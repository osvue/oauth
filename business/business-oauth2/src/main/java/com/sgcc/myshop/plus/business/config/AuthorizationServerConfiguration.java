package com.sgcc.myshop.plus.business.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/22 8:48
 * @version: 1.0
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Resource(name = "userDetailsServiceBean")
    @Qualifier(value = "userDetailsServiceBean")
    private UserDetailsService userDetailsServiceBean;
    /**
     * 注入用于支持 password 模式
     */
    @Autowired
    private AuthenticationManager authenticationManager;

     @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌保存到数据库
//        return new JdbcTokenStore(dataSource());
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
//        return new JdbcClientDetailsService(dataSource());
        return new CustomJdbcClientDetailsService(dataSource());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*DefaultTokenServices tokenServices = new DefaultTokenServices();
        //token持久化容器
        tokenServices.setTokenStore(tokenStore());
        //客户端信息
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());

        //access_token 的有效时长 (秒), 默认 12 小时
        tokenServices.setAccessTokenValiditySeconds(60*1);
        //refresh_token 的有效时长 (秒), 默认 30 天
        tokenServices.setRefreshTokenValiditySeconds(60*2);
        //是否支持refresh_token，默认false
        tokenServices.setSupportRefreshToken(true);
        //是否复用refresh_token,默认为true(如果为false,则每次请求刷新都会删除旧的refresh_token,创建新的refresh_token)
        tokenServices.setReuseRefreshToken(false);*/
        endpoints
                // 用于支持密码模式
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceBean)
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许客户端访问 /oauth/check_token 检查 token
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 客户端配置
        clients.withClientDetails(jdbcClientDetailsService());
    }
}
