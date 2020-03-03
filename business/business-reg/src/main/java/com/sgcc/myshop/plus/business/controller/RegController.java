package com.sgcc.myshop.plus.business.controller;

import com.sgcc.myshop.plus.common.ResultBean;
import com.sgcc.myshop.plus.provider.api.IUmsAdminService;
import com.sgcc.myshop.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/21 20:22
 * @version: 1.0
 */
@RestController
public class RegController {

    @Reference(version = "1.0.0")
    private IUmsAdminService echoService;


    @RequestMapping(value = "/echo" ,method = RequestMethod.GET)
    public ResultBean<List<UmsAdmin>> login(){
        return  new ResultBean<>(echoService.list());
    }
}
