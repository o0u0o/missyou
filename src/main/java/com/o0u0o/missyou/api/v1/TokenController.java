package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.core.enumeration.LoginType;
import com.o0u0o.missyou.core.http.NotFoundException;
import com.o0u0o.missyou.dto.TokenGetDTO;
import com.o0u0o.missyou.service.WxAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TokenController
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 3:26 下午
 * @Descripton: Token相关路由
 * @Version: v0.0.1
 **/
@RequestMapping(value = "token")
@RestController
public class TokenController {

    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData){
        Map<String, String> map = new HashMap<>();
        String token = null;
        //登录类型
        switch (userData.getType()){
            case USER_WX:
                token = wxAuthenticationService.code2Session(userData.getAccount());
                break;
            case USER_EMAIL:
                break;
            default:
                throw new NotFoundException(10003);
        }
        map.put("token", token);
        return map;
    }

}
