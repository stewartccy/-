package com.ccy.passbook.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.ccy.passbook.passbook.constant.Constants;
import com.ccy.passbook.passbook.mapper.PassTemplateRowMapper;
import com.ccy.passbook.passbook.service.IGainPassTemplateService;
import com.ccy.passbook.passbook.utils.RowKeyGenUtil;
import com.ccy.passbook.passbook.vo.GainPassTemplateRequest;
import com.ccy.passbook.passbook.vo.PassTemplate;
import com.ccy.passbook.passbook.vo.Response;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户领取优惠券功能实现
 * @author CCY
 * @date 2019/6/16 22:18
 */
@Slf4j
@Service
public class GainPassServiceImpl implements IGainPassTemplateService {

    private final HbaseTemplate hbaseTemplate;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public GainPassServiceImpl(HbaseTemplate hbaseTemplate, StringRedisTemplate redisTemplate) {
        this.hbaseTemplate = hbaseTemplate;
        this.redisTemplate = redisTemplate;
    }

    //给用户添加优惠券
    private boolean addPassForUser(GainPassTemplateRequest request, Integer merchansId,
                                   String passTemplateId) throws Exception{
        byte[] FAIMLY_I = Constants.PassTable.FAMILY_I.getBytes();
        byte[] USER_ID = Constants.PassTable.USER_ID.getBytes();
        byte[] TEMPLATE_ID = Constants.PassTable.TEMPLATE_ID.getBytes();
        byte[] TOKEN = Constants.PassTable.TOKEN.getBytes();
        byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
        byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

        List<Mutation> datas = new ArrayList<>();
        Put put = new Put(Bytes.toBytes(RowKeyGenUtil.genPassRowKey(request)));
        put.addColumn(FAIMLY_I,USER_ID,Bytes.toBytes(request.getUserId()));
        put.addColumn(FAIMLY_I,TEMPLATE_ID,Bytes.toBytes(passTemplateId));

        if(request.getPassTemplate().getHasToken()){
            String token = redisTemplate.opsForSet().pop(passTemplateId);
            if(null == token){
                log.error("Token Not exist: {}", passTemplateId);
                return false;
            }
            recordTokenToFile(merchansId,passTemplateId,token);
            put.addColumn(FAIMLY_I,TOKEN,Bytes.toBytes(token));
        }else {
            put.addColumn(FAIMLY_I,TOKEN,Bytes.toBytes("-1"));
        }

        put.addColumn(FAIMLY_I,ASSIGNED_DATE,Bytes.toBytes(
                DateFormatUtils.ISO_DATE_FORMAT.format(new Date())));
        put.addColumn(FAIMLY_I,CON_DATE,Bytes.toBytes("-1"));

        datas.add(put);

        hbaseTemplate.saveOrUpdates(Constants.PassTable.TABLE_NAME,datas);
        return true;
    }

    @Override
    public Response gainPassTemplate(GainPassTemplateRequest request) throws Exception {

        PassTemplate passTemplate;
        String passTemplateId = RowKeyGenUtil.genPassTemplateRowKey(request.getPassTemplate());

        try{
            passTemplate = hbaseTemplate.get(
                    Constants.PassTemplateTable.TABLE_NAME,
                    passTemplateId,
                    new PassTemplateRowMapper()
            );
        }catch (Exception ex){
            log.error("Gain PassTemplate Error: {}", JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("Gain PassTemplate Error!");
        }

        if(passTemplate.getLimit() <= 1 && passTemplate.getLimit() != -1){
            log.error("PassTemplate Limit Max: {}", JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("PassTemplate Limit Max!");
        }

        Date cur = new Date();
        if(!(cur.getTime() >= passTemplate.getStart().getTime() &&
                cur.getTime() < passTemplate.getEnd().getTime())){
            log.error("PassTemplate ValidTime error: {}", JSON.toJSONString(request.getPassTemplate()));
            return Response.failure("PassTemplate ValidTime Error!");
        }

        //减去优惠limit
        if(passTemplate.getLimit() != -1){
            List<Mutation> datas = new ArrayList<>();
            byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
            byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();

            Put put = new Put(Bytes.toBytes(passTemplateId));
            put.addColumn(FAMILY_C,LIMIT,
                    Bytes.toBytes(passTemplate.getLimit()-1));
            datas.add(put);

            hbaseTemplate.saveOrUpdates(Constants.PassTemplateTable.TABLE_NAME,datas);
        }

        //将优惠券保存到用户优惠券表
        if(!addPassForUser(request,passTemplate.getId(),passTemplateId)){
            return Response.failure("GainPassTemplate Failure!");
        }

        return Response.success();
    }

    //将已使用的token记录到文件中
    private void recordTokenToFile(Integer merchantsId, String PassTemplateId,
                                   String token) throws Exception{
        Files.write(
                Paths.get(Constants.TOKEN_DIR, String.valueOf(merchantsId),
                        PassTemplateId + Constants.USED_TOKEN_SUFFIX),
                (token + "\n").getBytes(),
                StandardOpenOption.APPEND

        );
    }
}
