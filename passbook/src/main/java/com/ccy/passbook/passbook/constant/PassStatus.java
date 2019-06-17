package com.ccy.passbook.passbook.constant;

/**
 * 优惠券状态
 * @author CCY
 * @date 2019/6/14 18:20
 */
public enum  PassStatus {

    UNUSED(1,"未使用"),
    USED(2,"已使用"),
    ALL(3,"全部领取的");

    private Integer code;
    private String desc;


    PassStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }
}
