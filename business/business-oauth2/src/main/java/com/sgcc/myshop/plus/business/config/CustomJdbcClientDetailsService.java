package com.sgcc.myshop.plus.business.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/26 16:22
 * @version: 1.0
 */

public class CustomJdbcClientDetailsService extends JdbcClientDetailsService {

    private static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            "web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            "from oauth_client_details where client_id = ?   ";


    public CustomJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
        setSelectClientDetailsSql(SELECT_CLIENT_DETAILS_SQL);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }


}