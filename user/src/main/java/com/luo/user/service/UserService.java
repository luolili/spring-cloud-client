package com.luo.user.service;

import com.luo.user.dataobject.UserInfo;

public interface UserService {

    UserInfo findByOpenid(String openid);
}
