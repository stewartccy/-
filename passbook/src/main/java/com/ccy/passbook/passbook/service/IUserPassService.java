package com.ccy.passbook.passbook.service;

import com.ccy.passbook.passbook.vo.Pass;
import com.ccy.passbook.passbook.vo.Response;

/**
 * 获取用户个人优惠券信息
 * @author CCY
 * @date 2019/6/16 0:25
 */
public interface IUserPassService {
    //获取用户个人优惠券信息，即我的优惠券功能实现
    Response getUserPassInfo(Long UserId) throws Exception;

    //获取用户已经消费了的优惠券，即已使用优惠券功能实现
    Response getUserUsedPassInfo(Long userId) throws Exception;

    //获取用户所有的优惠券
    Response getUserAllPassInfo(Long userId) throws Exception;

    //用户使用优惠券
    Response userUsePass(Pass pass);
}
