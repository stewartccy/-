package com.ccy.passbook.passbook.dao;

import com.ccy.passbook.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * merchants dao 接口
 * @author CCY
 * @date 2019/6/15 14:38
 */
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {
    Merchants findById(Integer id);

    Merchants findByName(String name);

    List<Merchants> findByIdIn(List<Integer> ids);
}
