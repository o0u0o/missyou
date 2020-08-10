package com.o0u0o.missyou.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @ClassName AutoPrefixUrlMapping
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/9 下午1:11
 * @Descripton: 自动补全路由URL
 * 1、需要继承 RequestMappingHandlerMapping 处理 被 @RequestMapping注解的Controller的
 * @Version: v0.0.1
 **/
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    @Value("${missyou.api-package}")
    private String apiPackagePath;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        //1、进行判断 是否为空
        if (mappingInfo != null){
            //3、修改路由信息(自动合并路由)
            String prefix = getPrefix(handlerType);
            //新的MappingInfo合并原有的MappingInfo()
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return mappingInfo;
    }

    /**
     * 获取前缀
     * @param handlerType
     * @return
     * 1、通过 handlerType 获取包名
     *
     */
    private String getPrefix(Class<?> handlerType){
        String packageName = handlerType.getPackage().getName();
        String dotPath = packageName.replaceAll(this.apiPackagePath, "");
        return dotPath.replace(".", "/");
    }
}
