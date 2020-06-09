package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.constant.CreateUserRequest;
import com.imooc.ad.constant.CreateUserResponse;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IAdUserService;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chenqiang
 * @create 2020-06-09 14:58
 */
@Slf4j
@Service
public class AdUserServiceImpl implements IAdUserService {

    @Autowired
    private AdUserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser adUser = userRepository.findByUsername(request.getUsername());
        if (adUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newAdUser = userRepository.save(new AdUser(request.getUsername(),
                CommonUtils.md5(request.getUsername())));

        return new CreateUserResponse(
                newAdUser.getId(), newAdUser.getUsername(),
                newAdUser.getToken(), newAdUser.getCreateTime(),
                newAdUser.getCreateTime());
    }
}
