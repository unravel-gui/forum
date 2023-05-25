package com.kemorebi.forum.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.dozermapper.core.Mapper;
import com.kemorebi.forum.utils.DozerUtils;
import com.kemorebi.forum.utils.jwt.client.JwtTokenClientUtils;
import com.kemorebi.forum.utils.jwt.properties.AuthProperties;
import com.kemorebi.forum.utils.jwt.server.JwtTokenServerUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({AuthProperties.class})
public class utilsBean {

    @Bean
    @ConditionalOnMissingBean
    public DozerUtils getDozerUtils(Mapper mapper) {
        DozerUtils dozerUtils = new DozerUtils(mapper);
        return dozerUtils;
    }


    @Bean
    @ConditionalOnMissingBean
    public JwtTokenClientUtils getJwtTokenClientUtils(AuthProperties authProperties) {
        return new JwtTokenClientUtils(authProperties);
    }

    @Bean
    public JwtTokenServerUtils getJwtTokenServerUtils(AuthProperties authProperties) {
        return new JwtTokenServerUtils(authProperties);
    }
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverterExtension();
        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;
        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter);
    }

    public class FastJsonHttpMessageConverterExtension extends FastJsonHttpMessageConverter {
        FastJsonHttpMessageConverterExtension() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
            mediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"));
            setSupportedMediaTypes(mediaTypes);
        }
    }


}
