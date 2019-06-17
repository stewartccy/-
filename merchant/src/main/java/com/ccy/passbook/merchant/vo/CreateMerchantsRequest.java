package com.ccy.passbook.merchant.vo;

import com.ccy.passbook.merchant.constant.ErrorCode;
import com.ccy.passbook.merchant.dao.MerchantsDao;
import com.ccy.passbook.merchant.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建商户请求对象
 * @author CCY
 * @date 2019/6/14 14:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {
    //商户名称
    private String name;

    //商户logo
    private String logoUrl;

    //商户营业执照
    private String businessLicenseUrl;

    //商户联系电话
    private String phone;

    //商户联系地址
    private String address;

    //验证请求的有效性
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(merchantsDao.findByName(this.name) != null){
            return ErrorCode.DUPLICATE_NAME;
        }
        if(null == this.logoUrl){
            return ErrorCode.EMPTY_LOGO;
        }
        if(null == this.businessLicenseUrl){
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }
        if(null == this.address){
            return ErrorCode.EMPTY_ADDRESS;
        }
        if(null == this.phone){
            return ErrorCode.ERROR_PHONE;
        }
        return ErrorCode.SUCCESS;
    }

    //将请求对象转换为商户对象
    public Merchants toMerchants(){
        Merchants merchants = new Merchants();

        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setPhone(phone);
        merchants.setAddress(address);

        return merchants;
    }
}
