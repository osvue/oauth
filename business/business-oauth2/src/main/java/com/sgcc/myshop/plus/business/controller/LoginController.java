package com.sgcc.myshop.plus.business.controller;

import com.google.common.collect.Maps;
import com.sgcc.myshop.plus.business.ProfileFeign;
import com.sgcc.myshop.plus.business.dto.LoginInfo;
import com.sgcc.myshop.plus.business.dto.LoginParam;
import com.sgcc.myshop.plus.common.ResultBean;
import com.sgcc.myshop.plus.commons.utils.MapperUtils;
import com.sgcc.myshop.plus.commons.utils.OkHttpClientUtil;
import com.sgcc.myshop.plus.provider.api.IUmsAdminService;
import com.sgcc.myshop.plus.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/22 14:21
 * @version: 1.0
 */
@RestController
public class LoginController {
    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Resource
    public TokenStore tokenStore;

    @Reference(version = "1.0.0")
    private IUmsAdminService echoService;

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;


    /**
     * 远程调用
     */
    @Resource
    public ProfileFeign profileFeign;

    @RequestMapping(value = "/user/login" ,method = RequestMethod.POST)
    public ResultBean<Map<String, Object>> login(@RequestBody LoginParam loginParam){
        Map<String, Object> result = Maps.newHashMap();

        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            // 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);

            // 发送登录日志
//            sendAdminLoginLog(userDetails.getUsername(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResultBean<>(result);
    }
    @GetMapping(value = "/user/info")
    public ResultBean<LoginInfo> userInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.err.println(name);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setAvatar("http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg");
        loginInfo.setName("MyNameIs 多米尼奥.托瑞托");
        try {
            String info = profileFeign.getInfo(name);
            System.out.println(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultBean<>(loginInfo);
    }

    @PostMapping(value = "/user/logout")
    public ResultBean<Void> logout(HttpServletRequest request) {
        // 获取 token
        String token = request.getParameter("access_token");
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResultBean<Void>( );
    }
}
