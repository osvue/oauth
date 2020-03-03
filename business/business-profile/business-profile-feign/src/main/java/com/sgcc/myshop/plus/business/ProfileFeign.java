package com.sgcc.myshop.plus.business;

import com.sgcc.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/23 10:53
 * @version: 1.0
 */
@FeignClient(value = "business-profile" ,configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {
    @GetMapping("/info/{username}")
    String getInfo(@PathVariable String username) ;
}
