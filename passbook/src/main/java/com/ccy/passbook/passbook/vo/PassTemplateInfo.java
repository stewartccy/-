package com.ccy.passbook.passbook.vo;

import com.ccy.passbook.passbook.constant.Constants;
import com.ccy.passbook.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 优惠券模板信息
 * @author CCY
 * @date 2019/6/15 23:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PassTemplateInfo extends PassTemplate {

    //优惠券模板
    private PassTemplate passTemplate;

    private Merchants merchants;


}
