package com.ccy.passbook.passbook.utils;

import com.ccy.passbook.passbook.vo.Feedback;
import com.ccy.passbook.passbook.vo.GainPassTemplateRequest;
import com.ccy.passbook.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Rowkey 生成器工具类
 * @author CCY
 * @date 2019/6/15 18:21
 */
@Slf4j
public class RowKeyGenUtil {
    //根据提供的passtemplate对象生成rowkey
    public static String genPassTemplateRowKey(PassTemplate passTemplate){
        String passInfo = String.valueOf(passTemplate.getId()) + "_" + passTemplate.getTitle();
        String rowKey = DigestUtils.md5Hex(passInfo);
        log.info("GenPassTemplateRowKey: {}, {}",passInfo,rowKey);

        return rowKey;
    }

    //根据提供的领取优惠券请求生成rowkey，只可以在领取优惠券的时候使用
    public static String genPassRowKey(GainPassTemplateRequest request){
        return new StringBuilder(String.valueOf(request.getUserId())).reverse().toString()
                + (Long.MAX_VALUE - System.currentTimeMillis())
                + genPassTemplateRowKey(request.getPassTemplate());
    }

    //根据feedb构造rowkey
    public static String genFeedbackRowKey(Feedback feedback){
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString() +
                (Long.MAX_VALUE - System.currentTimeMillis());
    }
}
