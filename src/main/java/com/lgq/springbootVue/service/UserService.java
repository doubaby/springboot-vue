package com.lgq.springbootVue.service;

import com.lgq.springbootVue.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author LGQ
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-08-03 21:36:27
*/
public interface UserService extends IService<User> {

    List<User> pageSelect(Integer pageNum, Integer pageSize, String username, String email);

    Integer selectTotal(String username, String email);

}
