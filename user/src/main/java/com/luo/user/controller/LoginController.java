package com.luo.user.controller;

import com.luo.user.constant.CookieConstant;
import com.luo.user.constant.RedisConstant;
import com.luo.user.dataobject.UserInfo;
import com.luo.user.enums.ResultEnum;
import com.luo.user.enums.RoleEnum;
import com.luo.user.service.UserService;
import com.luo.user.util.CookieUtils;
import com.luo.user.util.ResultVOUtils;
import com.luo.user.vo.ResultVO;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 买家登陆
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        //-1 从是数据库里查询user
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
        }
        if (userInfo.getRole() != RoleEnum.BUYER.getCode()) {
            return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
        }
        //-3 设置cookie
        CookieUtils.setCookie(response, CookieConstant.OPENID,openid, CookieConstant.expire);
        return ResultVOUtils.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest req,
                          HttpServletResponse response) {

        Cookie cookie = CookieUtils.get(req, CookieConstant.TOKEN);
        //如果登陆了
        if (cookie != null) {
            return ResultVOUtils.success();
        }
        //-1 从是数据库里查询user
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
        }
        if (userInfo.getRole() != RoleEnum.SELLER.getCode()) {
            return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
        }
        //-3 写入redis : key=uuid,
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire, TimeUnit.SECONDS);


        //-4 设置cookie
        CookieUtils.setCookie(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVOUtils.success();
    }
}
