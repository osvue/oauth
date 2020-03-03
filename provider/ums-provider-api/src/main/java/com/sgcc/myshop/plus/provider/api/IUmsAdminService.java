package com.sgcc.myshop.plus.provider.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sgcc.myshop.plus.provider.domain.UmsAdmin;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/21 17:33
 * @version: 1.0
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    public String echo(String ts);

    UmsAdmin queryByName(String name);

}
