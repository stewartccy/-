package com.ccy.passbook.merchant.service;

import com.ccy.passbook.merchant.vo.CreateMerchantsRequest;
import com.ccy.passbook.merchant.vo.PassTemplate;
import com.ccy.passbook.merchant.vo.Response;

/**
 * 对商户服务接口定义
 * @author CCY
 * @date 2019/6/14 14:54
 */

public interface IMerchantsServ {
    //创建商户服务
    Response createMerchants(CreateMerchantsRequest request);

    //根据id构造商户信息
    Response buildMerchantsInfoById(Integer id);

    //投放优惠券
    Response dropPassTemplate(PassTemplate template);
}
