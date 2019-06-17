package com.ccy.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户领取的优惠券
 * @author CCY
 * @date 2019/6/15 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pass {
    private Long userId;

    //pass在HBASE中的rowkey
    private String rowKey;

    //passtemplate在hbase中的rowkey
    private String templateId;

    //优惠券token
    private String token;

    private Date assignedDate;

    private Date conDate;
}
