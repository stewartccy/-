package com.ccy.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的错误信息
 * @author CCY
 * @date 2019/6/15 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo<T> {

    public static final Integer ERROR = -1;

    //特定错误码
    private Integer code;

    private String message;

    //请求url
    private String url;

    //请求返回的数据
    private T data;
}
