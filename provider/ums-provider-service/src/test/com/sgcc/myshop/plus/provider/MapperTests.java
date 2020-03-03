package com.sgcc.myshop.plus.provider;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sgcc.myshop.plus.provider.domain.UmsAdmin;
import com.sgcc.myshop.plus.provider.mapper.UmsAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author: GIF
 * @createDate: 2020/2/21 19:51
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTests {

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Test
    public void umsGet(){
        Wrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        List<UmsAdmin> lists = umsAdminMapper.selectList(wrapper);
        lists.forEach(System.out::println);
    }
}
