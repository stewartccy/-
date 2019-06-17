package com.ccy.passbook.passbook.vo;

import com.ccy.passbook.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户领取的优惠券信息
 * @author CCY
 * @date 2019/6/15 23:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInfo {
    private Pass pass;

    private PassTemplate passTemplate;

    private Merchants merchants;
}
