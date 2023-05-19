package com.kemorebi.forum.utils.jwt;

import com.kemorebi.forum.common.context.BaseContextConstants;
import com.kemorebi.forum.exception.BizException;
import com.kemorebi.forum.exception.code.ExceptionCode;
import com.kemorebi.forum.utils.DefaultValueHelper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

@Slf4j
public class JwtHelper {
    private static final RsaKeyHelper RSA_KEY_HELPER = new RsaKeyHelper();

    /**
     * 生成用户token
     * @param jwtUserInfo
     * @param priKeyPath 私钥路径
     * @param expire 有效时间
     * @return
     * 
     * */
    public static Token generateUserToken(JwtUserInfo jwtUserInfo, String priKeyPath, int expire) {
        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置主题
                .claim(BaseContextConstants.JWT_KEY_USER_ID, String.valueOf(jwtUserInfo.getUserId()))
                .claim(BaseContextConstants.JWT_KEY_ACCOUNT, jwtUserInfo.getAccount())
                .claim(BaseContextConstants.JWT_KEY_MOBILE, jwtUserInfo.getMobile())
                .claim(BaseContextConstants.JWT_KEY_ADMIN, jwtUserInfo.getAdmin());
        return generateToken(jwtBuilder, priKeyPath, expire);
    }

    /**
     * 获取token中的用户信息
     * @param token token
     * @param pubKeyPath 公钥路径
     * @return
     * @throws Exception
     */
    public static JwtUserInfo getJwtFromToken(String token, String pubKeyPath) {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        String strUserId = DefaultValueHelper.getObjectValue(body.get(BaseContextConstants.JWT_KEY_USER_ID));
        String account = DefaultValueHelper.getObjectValue(body.get(BaseContextConstants.JWT_KEY_ACCOUNT));
        String moile = DefaultValueHelper.getObjectValue(body.get(BaseContextConstants.JWT_KEY_MOBILE));

        boolean admin = DefaultValueHelper.getBooleanValue(body.get(BaseContextConstants.JWT_KEY_ADMIN));
        Long userId = DefaultValueHelper.longValueOf0(strUserId);
        return new JwtUserInfo(userId, account, moile, admin);
    }


    /**
     * 公钥解析token
     * @param token
     * @param pubKeyPath 公钥路径
     * @return
     * @throws Exception
     * */
    private static Jws<Claims> parserToken(String token, String pubKeyPath) {
        try {
            return Jwts.parser().setSigningKey(RSA_KEY_HELPER.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            // 过期
            throw new BizException(ExceptionCode.JWT_TOKEN_EXPIRED.getCode(), ExceptionCode.JWT_TOKEN_EXPIRED.getMsg());
        }catch (SignatureException e) {
            // 签名错误
            throw new BizException(ExceptionCode.JWT_SIGNATURE.getCode(), ExceptionCode.JWT_SIGNATURE.getMsg());
        } catch (InvalidKeySpecException e) {
            // token 为空
            throw new BizException(ExceptionCode.JWT_ILLEGAL_ARGUMENT.getCode(), ExceptionCode.JWT_ILLEGAL_ARGUMENT.getMsg());
        } catch (Exception e){
            log.error("errCode:{}, message:{}", ExceptionCode.JWT_PARSER_TOKEN_FAIL.getCode(), e.getMessage());
            throw new BizException(ExceptionCode.JWT_PARSER_TOKEN_FAIL.getCode(), ExceptionCode.JWT_PARSER_TOKEN_FAIL.getMsg());
        }
    }

    protected static Token generateToken(JwtBuilder jwtBuilder, String priKeyPath, int expire) {
        try {
            String compactJws = jwtBuilder.setExpiration(DefaultValueHelper.localDateTime2Date(LocalDateTime.now().plusSeconds(expire)))
                    // 设置是算法
                    .signWith(SignatureAlgorithm.RS256, RSA_KEY_HELPER.getPrivateKey(priKeyPath))
                    // 全部设置完成后拼成jwt串的方法
                    .compact();
            return new Token(compactJws, expire);
        }catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("errcode:{}, message:{}", ExceptionCode.JWT_GEN_TOKEN_FAIL.getCode(), e.getMessage());
            // 封装到自定义全局异常中
            throw new BizException(ExceptionCode.JWT_GEN_TOKEN_FAIL.getCode(), ExceptionCode.JWT_GEN_TOKEN_FAIL.getMsg());
        }
    }


}
