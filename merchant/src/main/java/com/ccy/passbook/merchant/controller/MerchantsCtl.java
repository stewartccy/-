package com.ccy.passbook.merchant.controller;

import com.alibaba.fastjson.JSON;
import com.ccy.passbook.merchant.service.IMerchantsServ;
import com.ccy.passbook.merchant.vo.CreateMerchantsRequest;
import com.ccy.passbook.merchant.vo.PassTemplate;
import com.ccy.passbook.merchant.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * 商户服务controller
 * @author CCY
 * @date 2019/6/14 16:28
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {

    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsCtl(IMerchantsServ merchantsServ) {
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){
        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }


    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo: {}",id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate: {}",passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }
}
