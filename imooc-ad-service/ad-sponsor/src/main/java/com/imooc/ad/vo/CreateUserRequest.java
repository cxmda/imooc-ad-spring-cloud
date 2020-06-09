package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author chenqiang
 * @create 2020-06-09 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    /**
     * 对username进行简单不为空的校验
     */
    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }
}
