package com.sgcc.myshop.plus.business.controller;

import com.sgcc.myshop.plus.common.ResultBean;
import com.sgcc.myshop.plus.provider.api.IUmsAdminService;
import com.sgcc.myshop.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/23 8:44
 * @version: 1.0
 */
@RestController
public class ProfileController {

    @Reference(version = "1.0.0")
    private IUmsAdminService umsAdminService;

    @GetMapping("/info/{username}")
    public ResultBean<List<Map<String, Object>> > getInfo(@PathVariable String username){
//        umsAdminService
        System.err.println(username);
        List<Map<String, Object>> maps = umsAdminService.listMaps();
        return new ResultBean<>(maps);
    }
}
