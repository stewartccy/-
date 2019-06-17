package com.ccy.passbook.passbook.service.impl;

import com.ccy.passbook.passbook.constant.Constants;
import com.ccy.passbook.passbook.dao.MerchantsDao;
import com.ccy.passbook.passbook.entity.Merchants;
import com.ccy.passbook.passbook.mapper.PassTemplateRowMapper;
import com.ccy.passbook.passbook.service.IInventoryService;
import com.ccy.passbook.passbook.service.IUserPassService;
import com.ccy.passbook.passbook.utils.RowKeyGenUtil;
import com.ccy.passbook.passbook.vo.*;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.LongComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取库存信息 只返回用户没有领取的
 * @author CCY
 * @date 2019/6/16 16:34
 */
@Slf4j
@Service
public class InventoryServiceImpl implements IInventoryService {

    private final MerchantsDao merchantsDao;

    private final HbaseTemplate hbaseTemplate;

    private final IUserPassService iUserPassService;

    @Autowired
    public InventoryServiceImpl(HbaseTemplate hbaseTemplate, MerchantsDao merchantsDao, IUserPassService iUserPassService) {
        this.hbaseTemplate = hbaseTemplate;
        this.merchantsDao = merchantsDao;
        this.iUserPassService = iUserPassService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response getInventoryInfo(Long userId) throws Exception {

        Response allUserPass = iUserPassService.getUserAllPassInfo(userId);

        List<PassInfo> passInfos = (List<PassInfo>)allUserPass.getData();

        List<PassTemplate> excludeObject =
                passInfos.stream().map(PassInfo::getPassTemplate).collect(Collectors.toList());
        List<String> excludeIds = new ArrayList<>();

        excludeObject.forEach(e -> excludeIds.add(
                RowKeyGenUtil.genPassTemplateRowKey(e)
        ));
        return new Response(new InvertoryResponse(
                userId,buildPassTemplateInfo(getAvailablePassTemplate(excludeIds))
        ));
    }

    //获取系统中可用的优惠券
    private List<PassTemplate> getAvailablePassTemplate(List<String> excludeIds){
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(
                new SingleColumnValueFilter(
                        Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                        Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                        CompareFilter.CompareOp.GREATER,
                        new LongComparator(0L)
                )
        );

        filterList.addFilter(
                new SingleColumnValueFilter(
                        Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                        Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                        CompareFilter.CompareOp.EQUAL,
                        new LongComparator(-1)
                )
        );

        Scan scan = new Scan();
        scan.setFilter(filterList);

        List<PassTemplate> validTemplates = hbaseTemplate.find(
                Constants.PassTemplateTable.TABLE_NAME,scan, new PassTemplateRowMapper()
        );
        List<PassTemplate> availablePassTemplate = new ArrayList<>();

        Date cur = new Date();

        for(PassTemplate validTemplate : validTemplates){
            if(excludeIds.contains(RowKeyGenUtil.genPassTemplateRowKey(validTemplate))){
                continue;
            }
            if(cur.getTime() >= validTemplate.getStart().getTime()
                && cur.getTime() <= validTemplate.getEnd().getTime()){
                availablePassTemplate.add(validTemplate);
            }
        }
        return availablePassTemplate;
    }

    //构造优惠券信息
    private List<PassTemplateInfo> buildPassTemplateInfo(List<PassTemplate> passTemplates){
        Map<Integer, Merchants> merchantsMap = new HashMap<>();
        List<Integer> merchantsIds = passTemplates.stream().map(
                PassTemplate::getId
        ).collect(Collectors.toList());
        List<Merchants> merchants = merchantsDao.findByIdIn(merchantsIds);
        merchants.forEach(m -> merchantsMap.put(m.getId(),m));

        List<PassTemplateInfo> result = new ArrayList<>(passTemplates.size());
        for(PassTemplate passTemplate : passTemplates){
            Merchants mc = merchantsMap.getOrDefault(passTemplate.getId(),
                   null);
            if (null == mc) {
                log.error("Merchants Error: {}", passTemplate.getId());
                continue;
            }
            result.add(new PassTemplateInfo(passTemplate,mc));
        }

        return result;
    }
}
