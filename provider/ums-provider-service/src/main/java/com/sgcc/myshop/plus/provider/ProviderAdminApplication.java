package com.sgcc.myshop.plus.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/21 17:37
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sgcc.myshop.plus.provider.mapper"})
public class ProviderAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderAdminApplication.class,args);
    }
}
