package com.o0u0o.missyou.lib;


import com.o0u0o.missyou.core.exception.http.ServerErrorException;

import java.io.*;

/**
 * 处理微信返回的通知
 * @author o0u0o
 * @date 2021/3/24 10:09 上午
 */
public class O0u0oWxNotify {

    /**
     * 读取微信返回的数据
     * 从流中读取返回的结构，并转换为字符串
     * @param stream
     * @return
     */
    public static String readNotify(InputStream stream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        //流转换为字符串
        StringBuilder builder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }
        catch (IOException e){
            throw new ServerErrorException(9999);
        }
        finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    public static String fail(){
        return "false";
    }

    /**
     * 微信对成功消息有规范，微信为xml
     * @return
     */
    public static String success(){
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

}
