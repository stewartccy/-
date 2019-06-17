package com.ccy.passbook.passbook.service;

import com.ccy.passbook.passbook.vo.GainPassTemplateRequest;
import com.ccy.passbook.passbook.vo.Response;

/**
 * 用户领取优惠券功能实现
 * @author CCY
 * @date 2019/6/16 0:24
 */
public interface IGainPassTemplateService {
    //用户领取优惠券
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;
}
