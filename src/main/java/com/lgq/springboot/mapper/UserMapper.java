package com.lgq.springboot.mapper;

import com.lgq.springboot.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author LGQ
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-08-03 21:36:27
 * @Entity springboot.pojo.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    List<User> pageSelect(@Param("pageNum") Integer pageNum,
                          @Param("pageSize") Integer pageSize,
                          @Param("userName") String userName,
                          @Param("email") String email);

    Integer selectTotal(@Param("userName") String username,
                        @Param("email") String email);


}




