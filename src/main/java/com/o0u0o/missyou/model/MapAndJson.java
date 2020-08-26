package com.o0u0o.missyou.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.o0u0o.missyou.core.http.ServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapAndJson
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/8/26 下午11:08
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
public class MapAndJson implements AttributeConverter<Map<String, Object>, String> {

    @Autowired
    private ObjectMapper mapper;

    /**
     * 转换为数据库中的字段
     * @param stringObjectMap
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        //序列化为String字符串
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            //先暂时打印堆栈信息，一般做法为 1、记录日志 2、往外抛异常
            e.printStackTrace();
            throw new ServerErrorException(999);
        }
    }

    /**
     * 转换为为实体
     * @param s
     * @return
     */
    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServerErrorException(999);
        }
    }
}
