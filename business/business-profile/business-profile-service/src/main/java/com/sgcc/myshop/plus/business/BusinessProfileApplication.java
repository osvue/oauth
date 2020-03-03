package com.sgcc.myshop.plus.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/22 17:42
 * @version: 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//开启服务发现
@EnableDiscoveryClient
public class BusinessProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileApplication.class, args);
    }
}
