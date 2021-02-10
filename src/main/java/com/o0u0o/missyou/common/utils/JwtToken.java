package com.o0u0o.missyou.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CommonUtil
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/10 2:32 下午
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
public class JwtToken {

    private static String jwtKey;

    private static Integer expiredTimeIn;

    @Value("${missyou.security.jwt-key}")
    private void setJwtKey(String jwtKey){
        JwtToken.jwtKey = jwtKey;
    }

    @Value("${missyou.security.token-expried-in}")
    private void setExpiredTimeIn(Integer expiredTimeIn){
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    /**
     * 生成token
     * @param uid 用户ID
     * @param scope 权限分级的数字
     * @return
     */
    public static String makeToken(Long uid, int scope){
        return JwtToken.getToken(uid, scope);
    }

    /**
     * 获取jwt令牌
     * www.jwt.io
     * 1、生产jwt常用两个库 jjwt库(中文资料多) auth0库(安全的产品多)
     * 2、使用前添加auth0的依赖
     * 这里使用auth0
     * @param uid
     * @param scope
     * @return
     */
    private static String getToken(Long uid, int scope){
        //1、生成令牌
        //1.1 选择一种算法: secret-随机字符串(盐)
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);

        Map<String, Date> map = JwtToken.calculateExpiredIssues();


        //1.2 生成令牌
        String token = JWT.create()
                //写入数据
                .withClaim("uid", uid)
                .withClaim("scope", scope)
                //过期时间
                .withExpiresAt(map.get("expiredTime"))
                //签发时间
                .withIssuedAt(map.get("now"))
                //生成签名方法
                .sign(algorithm);

        return token;
    }

    /**
     * 计算令牌过期时间
     * 返回当前时间(now)和过期时间(expiredTime)
     * @return
     */
    private static Map<String, Date> calculateExpiredIssues(){
        Map<String, Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, JwtToken.expiredTimeIn);
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }
}
