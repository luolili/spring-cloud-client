package com.luo.user.repo;

import com.luo.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}
