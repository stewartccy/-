package com.ccy.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户领取优惠券的请求对象
 * @author CCY
 * @date 2019/6/15 23:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GainPassTemplateRequest {
    private Long userId;

    private PassTemplate passTemplate;
}
