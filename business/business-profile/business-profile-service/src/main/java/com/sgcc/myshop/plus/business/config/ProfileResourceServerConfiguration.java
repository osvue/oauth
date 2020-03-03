package com.sgcc.myshop.plus.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description: 资源服务配置
 * @author: GIF
 * @createDate: 2020/2/24 15:42
 * @version: 1.0
 */

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ProfileResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.stateless(true);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
       /* http
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**")
        .hasAuthority("USER");*/
        //session创建策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //所有请求需要认证
        http.authorizeRequests().anyRequest().authenticated();
    }
    /*
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenServices(tokenServices())
                //资源ID
                .resourceId("resource_password_id");
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll();

        http.csrf().disable();
    }
    *//**
     * @Description OAuth2 token持久化接口，jwt不会做持久化处理
     * @Date 2019/7/15 18:12
     * @Version  1.0
     *//*
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    *//**
     * @Description 令牌服务
     * @Date 2019/7/15 18:07
     * @Version  1.0
     *//*
    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        return defaultTokenServices;
    }
    *//**
     * @Description 自定义token令牌增强器
     * @Date 2019/7/11 16:22
     * @Version  1.0
     *//*
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }
    *//**
     * @Description 加密方式
     * @Date 2019/7/15 18:06
     * @Version  1.0
     *//*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/


}

