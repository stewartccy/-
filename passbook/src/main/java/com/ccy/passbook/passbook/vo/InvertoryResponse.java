package com.ccy.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 库存请求响应
 * @author CCY
 * @date 2019/6/15 23:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvertoryResponse {

    private Long userId;

    //优惠券模板信息
    private List<PassTemplateInfo> passTemplateInfos;
}
