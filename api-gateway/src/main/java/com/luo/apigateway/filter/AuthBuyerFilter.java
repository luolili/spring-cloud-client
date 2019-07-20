package com.luo.apigateway.filter;

import com.luo.apigateway.constant.RedisConstant;
import com.luo.apigateway.util.CookieUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限验证：买家
 */
@Component
public class AuthBuyerFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {

        //-1 获取当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //-2 获取到请求对象
        HttpServletRequest request = currentContext.getRequest();
        if ("/order/order/create".equals(request.getRequestURI())) {
            return true;
        }
        return false;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Object run() throws ZuulException {
        //-1 获取当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //-2 获取到请求对象
        HttpServletRequest request = currentContext.getRequest();
        // order/create 只能买家访问； order/finish 只能卖家访问；
        if ("/order/order/create".equals(request.getRequestURI())) {
            Cookie cookie = CookieUtils.get(request, "openid");
            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
                //无权限
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }

        return null;
    }
}
