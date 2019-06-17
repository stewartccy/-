package com.ccy.passbook.passbook.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志生成器
 * @author CCY
 * @date 2019/6/15 14:47
 */
@Slf4j
public class LogoGenerator {
    //生成log
    public static void genLog(HttpServletRequest request, Long userId,
                              String action,Object info){
        log.info(
                JSON.toJSONString(
                        new LogObject(action,userId,System.currentTimeMillis(),
                                request.getRemoteAddr(),info)
                )
        );
    }
}
