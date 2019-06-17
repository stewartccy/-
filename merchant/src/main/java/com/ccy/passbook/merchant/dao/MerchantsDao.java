package com.ccy.passbook.merchant.dao;

import com.ccy.passbook.merchant.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * merchants dao 接口
 * @author CCY
 * @date 2019/6/13 18:17
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer> {
    //根据id获取商户对象
    Merchants findById(Integer id);

    //根据商户名称获取商户对象
    Merchants findByName(String name);

}
