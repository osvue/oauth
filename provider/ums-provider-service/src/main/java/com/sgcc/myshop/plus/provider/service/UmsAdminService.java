package com.sgcc.myshop.plus.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sgcc.myshop.plus.provider.api.IUmsAdminService;
import com.sgcc.myshop.plus.provider.domain.UmsAdmin;
import com.sgcc.myshop.plus.provider.mapper.UmsAdminMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/21 17:45
 * @version: 1.0
 */
@Service(version = "1.0.0")
public class UmsAdminService extends ServiceImpl<UmsAdminMapper,UmsAdmin> implements IUmsAdminService {
    @Resource
    UmsAdminMapper umsAdminMapper;

    @Override
    public String echo(String ts) {
        return "hello dubbo";
    }

    @Override
    public UmsAdmin queryByName(String name) {
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",name);
        return umsAdminMapper.selectOne(queryWrapper);
    }
}
