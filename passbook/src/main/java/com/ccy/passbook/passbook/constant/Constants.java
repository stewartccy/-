package com.ccy.passbook.passbook.constant;

/**
 * @author CCY
 * @date 2019/6/15 13:58
 */
public class   Constants {
    //商户优惠券kafka topic
    public static final String TEMPLATE_TOPIC = "merchants-template";

    //token文件存储目录
    public static final String TOKEN_DIR = "/tmp/token/";

    //已使用的token文件名后缀
    public static final String USED_TOKEN_SUFFIX = "_";

    //用户数的redis key
    public static final String USE_COUNT_REDIS_KEY = "CCY-user-count";

    //User Hbase Table
    public class UserTable{
        //User HBase表名
        public static final String TABLE_NAME = "pb:user";

        //基本信息列族
        public static final String FAMILY_B = "b";

        public static final String NAME = "name";

        public static final String AGE = "age";

        public static final String SEX = "sex";

        //额外信息列族
        public static final String FAMILY_O = "o";

        public static final String PHONE = "phone";

        public static final String ADDRESS = "address";
    }

    //passtemplate Hbase table
    public class PassTemplateTable{
        //passtemplate hbase 表名
        public static final String TABLE_NAME = "pb:passtemplate";

        public static final String FAMILY_B = "b";

        public static final String ID = "id";

        public static final String TITLE = "title";

        public static final String SUMMARY = "summary";

        public static final String DESC = "desc";

        public static final String HAS_TOKEN = "has_token";

        public static final String BACKGROUND = "background";

        public static final String FAMILY_C = "c";

        public static final String LIMIT = "limit";

        public static final String START = "start";

        public static final String END = "end";
    }

    //pass hbase table
    public class PassTable{
        public static final String TABLE_NAME = "pb:pass";

        public static final String FAMILY_I = "i";

        public static final String USER_ID = "user_id";

        public static final String TEMPLATE_ID = "template_id";

        public static final String TOKEN = "token";

        public static final String ASSIGNED_DATE = "assigned_date";

        //消费日期
        public static final String CON_DATE = "con_date";
    }

    public class Feedback{
        public static final String TABLE_NAME = "pb:feedback";

        public static final String FAMILY_I = "i";

        public static final String USER_ID = "user_id";

        public static final String TYPE = "type";

        public static final String TEMPLATE_ID = "template_id";

        public static final String COMMENT = "comment";
    }

}
