package com.lgq.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.lgq.springboot.pojo.User;
import com.lgq.springboot.service.UserService;
import com.lgq.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGQ
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2022-08-03 21:36:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> pageSelect(Integer pageNum, Integer pageSize, String username, String email) {
        PageHelper.startPage(pageNum, pageSize, "id desc");
        return userMapper.pageSelect(pageNum, pageSize, username, email);
    }

    @Override
    public Integer selectTotal(String username, String email) {
        return (userMapper.selectTotal(username, email));
    }


}




