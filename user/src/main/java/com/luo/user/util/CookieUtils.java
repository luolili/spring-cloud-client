package com.luo.user.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static void setCookie(HttpServletResponse response,
                          String name,
                          String value,
                          int maxAge) {
        //-1 创建cookie
        Cookie cookie = new Cookie(name, value);
        //-2 set path
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
