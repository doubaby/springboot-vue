package com.lgq.springbootVue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgq.springbootVue.dto.UserDto;

/**
* @author LGQ
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-08-03 21:36:27
*/
public interface UserDtoService extends IService<UserDto> {
    UserDto login(UserDto userDto);

    UserDto register(UserDto userDto);

}
