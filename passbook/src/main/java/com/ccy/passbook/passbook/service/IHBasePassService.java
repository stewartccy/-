package com.ccy.passbook.passbook.service;

import com.ccy.passbook.passbook.vo.PassTemplate;

/**
 * pass hbase服务
 * @author CCY
 * @date 2019/6/15 18:42
 */
public interface IHBasePassService {
    //passtemplate写入hbase
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
