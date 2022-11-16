package com.lgq.springbootVue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgq.springbootVue.dto.UserDto;
import org.springframework.stereotype.Repository;

/**
 * @author LGQ
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-08-03 21:36:27
 * @Entity springboot.pojo.User
 */
@Repository
public interface UserDtoMapper extends BaseMapper<UserDto> {

}




