package com.o0u0o.missyou.simple.database;

/**
 * @Author aiuiot
 * @Date 2020/3/15 12:26 下午
 * @Descripton:
 **/
public class MySQL implements IConnect {

    /** IP */
    private String ip;

    /** 端口号  */
    private Integer port;

    public MySQL(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public MySQL() {

    }

    @Override
    public void connect() {
        System.out.println(this.ip +":"+ this.port);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
