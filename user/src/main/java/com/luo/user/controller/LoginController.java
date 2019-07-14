package com.luo.user.controller;

import com.luo.user.constant.CookieConstant;
import com.luo.user.dataobject.UserInfo;
import com.luo.user.enums.ResultEnum;
import com.luo.user.enums.RoleEnum;
import com.luo.user.service.UserService;
import com.luo.user.util.CookieUtils;
import com.luo.user.util.ResultVOUtils;
import com.luo.user.vo.ResultVO;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
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
    public ResultVO seller(@RequestParam("openid") String openid,
                          HttpServletResponse response) {

        //-1 从是数据库里查询user
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_FAIL);
        }
        if (userInfo.getRole() != RoleEnum.BUYER.getCode()) {
            return ResultVOUtils.error(ResultEnum.ROLE_ERROR);
        }
        //-3 写入redis : key=uuid,


        //-4 设置cookie
        CookieUtils.setCookie(response, CookieConstant.OPENID,openid, CookieConstant.expire);
        return ResultVOUtils.success();
    }
}
