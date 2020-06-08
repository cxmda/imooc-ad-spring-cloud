package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测试数据
 * @author chen
 * @create 2020-06-07 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    public CommonResponse(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
