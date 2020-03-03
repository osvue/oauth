package com.sgcc.myshop.plus.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/21 20:21
 * @version: 1.0
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BusinessRegApplication {
    public static void main(String[] args) {

        SpringApplication.run(BusinessRegApplication.class,args);
    }
}
