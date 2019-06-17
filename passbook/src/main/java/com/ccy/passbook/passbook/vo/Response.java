package com.ccy.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller统一响应
 * @author CCY
 * @date 2019/6/15 22:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer errorCode = 0;
    private String errorMsg = "";
    private Object data;

    public Response(Object data){
        this.data = data;
    }

    public static Response success(){
        return new Response();
    }

    public static Response failure(String errorMsg){
        return new Response(-1,errorMsg,null);
    }
}
