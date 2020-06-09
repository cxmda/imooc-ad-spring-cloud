package com.imooc.ad.service;

import com.imooc.ad.vo.CreateUserRequest;
import com.imooc.ad.vo.CreateUserResponse;
import com.imooc.ad.exception.AdException;

/**
 * @author chenqiang
 * @create 2020-06-09 14:51
 */
public interface IAdUserService {
    /**
     * 创建用户
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
