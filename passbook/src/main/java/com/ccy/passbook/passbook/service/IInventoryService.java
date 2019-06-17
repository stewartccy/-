package com.ccy.passbook.passbook.service;

import com.ccy.passbook.passbook.vo.Response;

/**
 * 获取库存信息：只返回用户没有领取的优惠券，即优惠券库存功能实现接口定义
 * @date 2019/6/16 0:22
 */
public interface IInventoryService {
    //获取库存信息
    Response getInventoryInfo(Long userId) throws  Exception;
}
