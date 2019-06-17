package com.ccy.passbook.merchant.security;

/**
 * 用threadlocal去单独存储每一个线程携带的token信息
 * @author CCY
 * @date 2019/6/13 17:40
 */
public class AccessContext {
    private static final ThreadLocal<String> token = new ThreadLocal<String>();

    public static String getToken(){
        return token.get();
    }

    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }

    public static void clearAccessKey(){
        token.remove();
    }
}
