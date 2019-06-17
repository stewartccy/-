package com.ccy.passbook.merchant.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应对象
 * @author CCY
 * @date 2019/6/13 18:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    //错误码
    private Integer errorCode=0;

    //错误信息
    private String errorMsg="";

    //返回值对象
    private Object data;

    //正确的响应构造函数
    public Response(Object data){
        this.data = data;
    }
}
