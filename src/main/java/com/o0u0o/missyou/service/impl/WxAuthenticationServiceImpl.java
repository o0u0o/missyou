package com.o0u0o.missyou.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.o0u0o.missyou.common.utils.JwtToken;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.model.User;
import com.o0u0o.missyou.repository.UserRepository;
import com.o0u0o.missyou.service.WxAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName WxAuthenticationServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 4:41 下午
 * @Descripton: 微信登录验证业务实现
 * @Version: v0.0.1
 **/
@Service
public class WxAuthenticationServiceImpl implements WxAuthenticationService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Value("${wx.code2session-url}")
    private String code2SessionUrl;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    /**
     * 1、使用code码换取openid
     * 2、未注册用户注册(将微信用户写入到用户信息)
     * 3、已注册用户登录(使用openid查询用户信息)
     * 4、返回用户id
     * 5、将uid(用户id)写入jwt令牌，并返回到客户端（小程序）
     * @param code
     * @return
     */
    @Override
    public String code2Session(String code) {
        //1、向微信服务器验证用户身份
        //1.1 使用MessageFormat将参数appid、appsecret和code拼接到微信服务器url
        String url = MessageFormat.format(this.code2SessionUrl, this.appid, this.appsecret, code);

        //2.2 发送http请求调用微信服务器
        RestTemplate rest = new RestTemplate();
        //参数1 请求的url，参数2:返回类型
        String sessionText = rest.getForObject(url, String.class);

        //2.3 反序列化为Map类型得到openid
        Map<String, Object> session = new HashMap<>();
        try {
            //使用jackson将字符串类型反序列化为Map类型
            session = mapper.readValue(sessionText, Map.class);
        } catch (JsonProcessingException e) {
            //TODO 2.4 容错处理(对每个错误码作出反应)
            e.printStackTrace();
        }

        //2、颁布令牌并返回
        return this.registerUser(session);
    }

    /**
     * 使用openid注册用户
     * @param session
     * @return
     */
    private String registerUser(Map<String, Object> session){
        //1、取出openid
        String openid = (String)session.get("openid");
        if (openid == null){
            //1.2 抛出参数异常
            throw new ParameterException(20004);
        }

        //2、使用openid查询是否注册 如果未注册，写入数在颁布令牌
        Optional<User> userOptional = this.userRepository.findByOpenid(openid);
        if (userOptional.isPresent()){
            //返回jwt令牌 小程序用户都是同一个scope 数字等级
            return JwtToken.makeToken(userOptional.get().getId());
        }

        //2.2 如果不存在 先新注册用户 在返回jwt令牌
        User user = User.builder()
                .openid(openid)
                .build();
        userRepository.save(user);
        Long uid = user.getId();
        //返回JWT令牌
        return JwtToken.makeToken(uid);
    }
}
