package com.lgq.springbootVue.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Setter;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@Setter
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    @JsonIgnore
    private String password;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private String gender;

    /**
     * 
     */
    private String email;

    private String nickname;

    private String personphoto;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}