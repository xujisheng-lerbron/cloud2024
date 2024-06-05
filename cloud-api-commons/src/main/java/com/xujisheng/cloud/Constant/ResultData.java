package com.xujisheng.cloud.Constant;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  统一返回值处理
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;
    private String message;
    private T data;
    private Long timestamp;

    public ResultData(){
        this.timestamp=System.currentTimeMillis();
    }

    public static <T> ResultData <T> success(T data){
        ResultData resultData = new ResultData();

        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);

        return resultData;
    }

    public static <T> ResultData <T> fail(String code, String message){
        ResultData resultData = new ResultData();

        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
