package com.o0u0o.missyou.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName CommonUtil
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/10 2:32 下午
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Component
public class JwtToken {

    private static String jwtKey;

    /** 过期时间 */
    private static Integer expiredTimeIn;

    /** 默认等级为8 */
    private static Integer defaultScope = 8;



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
     * @param uid 用户id
     * @return
     */
    public static String makeToken(Long uid){
        return JwtToken.getToken(uid, defaultScope);
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
     * 校验令牌
     * @param token
     * @return
     */
    public static Optional<Map<String, Claim>> getClaims(String token){
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        //解析token
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e){
            //没必要记录日志
            return Optional.empty();
        }
        return Optional.of(decodedJWT.getClaims());
    }

    /**
     * 验证Token
     * @param token
     * @return
     */
    public static Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (JWTVerificationException e){
            //不合法返回false
            return false;
        }
        return true;
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
        //1.1 选择一种算法: secret-随机字符串(盐) 相当于钥匙
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
