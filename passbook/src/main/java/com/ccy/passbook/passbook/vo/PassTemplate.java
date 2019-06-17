package com.ccy.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 投放的优惠券对象定义
 * @author CCY
 * @date 2019/6/15 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplate {
    //所属商户id
    private Integer id;

    //优惠券标题
    private String title;

    //优惠券摘要
    private String summary;

    //优惠券详细信息
    private String desc;

    //最大个数限制
    private Long limit;

    //优惠券是否有token 用户商户核销
    private Boolean hasToken;

    private Integer background;

    private Date start;

    private Date end;
}
