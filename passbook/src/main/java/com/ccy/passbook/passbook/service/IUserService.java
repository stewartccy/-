package com.ccy.passbook.passbook.service;

import com.ccy.passbook.passbook.vo.Response;
import com.ccy.passbook.passbook.vo.User;

/**
 * 用户服务 创建User服务
 * @author CCY
 * @date 2019/6/15 22:16
 */
public interface IUserService {
    Response createUser(User user) throws Exception;
}
