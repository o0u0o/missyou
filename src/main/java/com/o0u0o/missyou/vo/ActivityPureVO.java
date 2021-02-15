package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ClassName ActivityPureVO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/15 6:24 下午
 * @Descripton: 活动精简VO（去掉复杂的关联数据）
 * @Version: v0.0.1
 **/
@Getter
@Setter
@NoArgsConstructor
public class ActivityPureVO {
    /** 主键ID */
    private Integer id;

    /** 活动标题 */
    private String title;

    /** 活动入口的图片 */
    private String entranceImg;

    /** 是否在线 */
    private Boolean online;

    /** 备注 */
    private String remark;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /** 活动名称 */
    private String name;

    public ActivityPureVO(Activity activity){
        BeanUtils.copyProperties(activity, this);
    }

    public ActivityPureVO(Object object){
        BeanUtils.copyProperties(object, this);
    }


}
