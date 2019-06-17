package com.ccy.passbook.passbook.mapper;

import com.ccy.passbook.passbook.constant.Constants;
import com.ccy.passbook.passbook.vo.PassTemplate;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * HBase passtemplate row to passtemplate object
 * @author CCY
 * @date 2019/6/15 15:49
 */
public class PassTemplateRowMapper implements RowMapper<PassTemplate> {

    private static byte[] FAMILY_B = Constants.PassTemplateTable.FAMILY_B.getBytes();
    private static byte[] ID = Constants.PassTemplateTable.ID.getBytes();
    private static byte[] TITLE = Constants.PassTemplateTable.TITLE.getBytes();
    private static byte[] SUMMARY = Constants.PassTemplateTable.SUMMARY.getBytes();
    private static byte[] DESC = Constants.PassTemplateTable.DESC.getBytes();
    private static byte[] HASTOKEN = Constants.PassTemplateTable.HAS_TOKEN.getBytes();
    private static byte[] BACKGROUND = Constants.PassTemplateTable.BACKGROUND.getBytes();

    private static byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
    private static byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();
    private static byte[] START = Constants.PassTemplateTable.START.getBytes();
    private static byte[] END = Constants.PassTemplateTable.END.getBytes();


    @Override
    public PassTemplate mapRow(Result result, int i) throws Exception {

        PassTemplate passTemplate = new PassTemplate();

        passTemplate.setId(Bytes.toInt(result.getValue(FAMILY_B,ID)));
        passTemplate.setTitle(Bytes.toString(result.getValue(FAMILY_B,TITLE)));
        passTemplate.setSummary(Bytes.toString(result.getValue(FAMILY_B,SUMMARY)));
        passTemplate.setDesc(Bytes.toString(result.getValue(FAMILY_B,DESC)));
        passTemplate.setHasToken(Bytes.toBoolean(result.getValue(FAMILY_B,HASTOKEN)));
        passTemplate.setBackground(Bytes.toInt(result.getValue(FAMILY_B,BACKGROUND)));

        String[] pattern = new String[] {"yyyy-MM-dd"};

        passTemplate.setLimit(Bytes.toLong(result.getValue(FAMILY_C,LIMIT)));
        passTemplate.setStart(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C,START)),pattern));
        passTemplate.setEnd(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C,END)),pattern));

        return passTemplate;
    }
}
