package com.lgq.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgq.springboot.dto.UserDto;
import com.lgq.springboot.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LGQ
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-08-03 21:36:27
 * @Entity springboot.pojo.User
 */
@Repository
public interface UserDtoMapper extends BaseMapper<UserDto> {

}




