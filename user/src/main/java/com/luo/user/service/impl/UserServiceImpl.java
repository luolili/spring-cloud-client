package com.luo.user.service.impl;

import com.luo.user.dataobject.UserInfo;
import com.luo.user.repo.UserInfoRepo;
import com.luo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepo.findByOpenid(openid);
    }
}
