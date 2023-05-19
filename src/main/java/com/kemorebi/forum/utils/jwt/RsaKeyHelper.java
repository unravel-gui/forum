package com.kemorebi.forum.utils.jwt;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Rsa key 帮助类
 * */
public class RsaKeyHelper {

    /**
     * 获取公钥，用于解析token
     * @param filename 密钥文件
     * */
    public PublicKey getPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 根据文件名获得输入流
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        try (DataInputStream dis = new DataInputStream(resourceAsStream)) {
            byte[] keyBytes = new byte[resourceAsStream.available()];
            dis.readFully(keyBytes);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        }
    }

    /**
     * 获取密钥 用于生成token
     * @param filename 密钥文件
     * */
    public PrivateKey getPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        try (DataInputStream dis = new DataInputStream(resourceAsStream)) {
            byte[] keyBytes = new byte[resourceAsStream.available()];
            dis.readFully(keyBytes);

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        }
    }

}
