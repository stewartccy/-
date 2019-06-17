package com.ccy.passbook.passbook.service;

import com.ccy.passbook.passbook.vo.Feedback;
import com.ccy.passbook.passbook.vo.Response;

/**
 * 评论功能：用户评论相关功能实现
 * @author CCY
 * @date 2019/6/15 23:18
 */
public interface IFeedbackService {
    Response createFeedback(Feedback feedback);

    Response getFeedback(Long userId);
}
