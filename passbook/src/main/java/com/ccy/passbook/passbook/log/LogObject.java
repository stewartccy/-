package com.ccy.passbook.passbook.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志对象
 * @author CCY
 * @date 2019/6/15 14:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogObject {
    //日志动作类型
    private String action;

    //用户id
    private Long userId;

    //当前时间
    private Long timestamp;

    //客户端ip地址
    private String remoteIp;

    //日志信息
    private Object info = null;
}
