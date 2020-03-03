package com.sgcc.myshop.plus.configuration;

import com.sgcc.myshop.plus.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 全局配置
 * @author THE GIFTED
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

}
