package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.dto.TokenGetDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("getToken")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData){
        switch ("123"){

        }
        return null;
    }

}
