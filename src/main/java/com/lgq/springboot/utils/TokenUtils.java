package com.lgq.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author LGQ
 * @createTime 2022/8/7 15:05
 * @description
 */
@SuppressWarnings("all")
public class TokenUtils {
public static  String generateTokenString(String userId,String sign){
    return JWT.create().withAudience(userId)//将userid保存到token里面，作为载荷
            .withExpiresAt(DateUtil.offsetHour(new Date(),6))//2小时后token过期
            .sign(Algorithm.HMAC256(sign));  //以password作为token的密钥
}

}
