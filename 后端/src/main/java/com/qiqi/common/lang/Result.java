package com.qiqi.common.lang;

import lombok.Data;
import sun.security.util.Resources_sv;

import java.io.Serializable;
@Data
public class Result implements Serializable {
    private  int code;
    private String message;
    private Object data;


    public static Result suscc(Object data) {

        return  Result.suscc(200,"操作成功",data);
    }

    public static Result suscc(int code, String message, Object data) {
      Result result=new Result();
            result.setCode(code);
            result.setMessage(message);
            result.setData(data);
            return  result;
    }

    public static Result fail(int code, String message,Object data) {
        Result result=new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return  result;
    }

    public static Result fail( String message, Object data) {
        return  Result.fail(400,message,null);
    }
    public static Result fail(String message) {

        return Result.fail(400,message,null);
    }


}
