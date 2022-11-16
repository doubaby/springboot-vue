package com.lgq.springbootVue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgq.springbootVue.mapper.UserMapper;
import com.lgq.springbootVue.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    Page<User> contextLoads() {
        Page<User> page = new Page<>(3, 5);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        return userPage;
    }

}
