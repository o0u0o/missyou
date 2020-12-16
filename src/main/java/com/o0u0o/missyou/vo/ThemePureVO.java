package com.o0u0o.missyou.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 精简的主题VO
 * @author mac
 * @date 2020/12/16 3:34 下午
 */
@Getter
@Setter
public class ThemePureVO {
    private Long id;
    private String title;
    private String description;
    private String name;
    private String entranceImg;
    private String extend;
    private String internalTopImg;
    private String titleImg;
    private String tplName;
    private Boolean online;
}
