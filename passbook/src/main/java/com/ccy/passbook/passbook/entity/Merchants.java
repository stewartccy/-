package com.ccy.passbook.passbook.entity;

import groovy.transform.BaseScript;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * 商户对象模型
 * @author CCY
 * @date 2019/6/15 14:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchants {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Basic
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @Basic
    @Column(name = "business_license_url",nullable = false)
    private String businessLicenseUrl;

    @Basic
    @Column(name = "phone",nullable = false)
    private String phone;

    @Basic
    @Column(name = "address",nullable = false)
    private String address;

    @Basic
    @Column(name = "is_audit",nullable = false)
    private Boolean isAudit;
}
