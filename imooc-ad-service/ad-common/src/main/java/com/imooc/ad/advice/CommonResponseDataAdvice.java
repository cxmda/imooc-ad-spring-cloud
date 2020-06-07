package com.imooc.ad.advice;

import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author chen
 * @create 2020-06-07 14:15
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter returnType, Class converterType) {
        if(returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        if(returnType.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        CommonResponse commonResponse = new CommonResponse(0,"");
        if(body == null){
            return body;
        }else if(body instanceof CommonResponse){
            commonResponse = (CommonResponse) body;
        }else{
            commonResponse.setData(body);
        }
        return commonResponse;
    }
}