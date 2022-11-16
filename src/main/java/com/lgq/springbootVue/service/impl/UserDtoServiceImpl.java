package com.lgq.springbootVue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgq.springbootVue.common.Status;
import com.lgq.springbootVue.dto.UserDto;
import com.lgq.springbootVue.exception.ServiceException;
import com.lgq.springbootVue.mapper.UserDtoMapper;
import com.lgq.springbootVue.service.UserDtoService;
import org.springframework.stereotype.Service;

/**
 * @author LGQ
 * @createTime 2022/8/6 16:22
 * @description
 */
@Service
@SuppressWarnings("all")
public class UserDtoServiceImpl extends ServiceImpl<UserDtoMapper, UserDto> implements UserDtoService {

    private static final Log LOG = Log.get();


    @Override
    public UserDto login(UserDto userDto) {

        UserDto one = getUserDTOInfo(userDto);
        //判断是否不存在
        if (one != null) {
            BeanUtil.copyProperties(one, userDto, true);
            return userDto;
        } else {
            throw new ServiceException(Status.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public UserDto register(UserDto userDto) {
        UserDto one = getUserDTOInfo(userDto);
        if (one == null) {
            one = new UserDto();
            BeanUtil.copyProperties(userDto, one, true);
            save(one);//把copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Status.CODE_600, "用户名已存在");
        }
        return one;
    }



    private UserDto getUserDTOInfo(UserDto userDto) {
        QueryWrapper<UserDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDto.getUsername());
        queryWrapper.eq("password", userDto.getPassword());
        UserDto one;
        try {
            //从数据库查询用户信息
            one = getOne(queryWrapper);
        } catch (Exception e) {
            //如果查出多个
            LOG.error(e);
            throw new ServiceException(Status.CODE_500, "系统错误");
        }
        return one;
    }
}