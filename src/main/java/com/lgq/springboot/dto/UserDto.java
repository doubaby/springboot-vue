package com.lgq.springboot.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LGQ
 * @createTime 2022/8/6 15:03
 * @description
 */
@TableName("t_login")
@Data
public class UserDto implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String personphoto;
    private String nickname;
    private String token;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
