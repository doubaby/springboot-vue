package com.lgq.springboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lgq.springboot.dto.UserDto;
import com.lgq.springboot.exception.ServiceException;
import com.lgq.springboot.service.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LGQ
 * @createTime 2022/8/7 16:38
 * @description
 */
public class tokenHandler implements HandlerInterceptor {
    @Autowired
    private UserDtoService userDtoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String token = request.getHeader("token");
        //如果不是映射方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException(Status.CODE_401, "无token,请重新登录");
        }
        //获取token中的userid
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j) {
            throw new ServiceException(Status.CODE_401, "token验证失败,请重新登录");
        }
        //根据token的userid查询数据库
        UserDto userDto = userDtoService.getById(userId);
        if (userDto == null) {
            throw new ServiceException(Status.CODE_401, "用户不存在,请重新登录");
        }
        //用户密码加签验证token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(userDto.getPassword())).build();
        try{
            //验证token
            verifier.verify(token);
        }catch (JWTVerificationException e){
            throw new ServiceException(Status.CODE_401, "token验证失败,请重新登录");
        }
        return  true;
    }
}
