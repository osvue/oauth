package com.sgcc.myshop.plus.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description: Oauth 2 认证服务器
 * @author: GIF
 * @createDate: 2020/2/22 8:43
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class BusinessOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(BusinessOAuth2Application.class,args);
    }

}
