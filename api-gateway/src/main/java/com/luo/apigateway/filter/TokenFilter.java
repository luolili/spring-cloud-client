package com.luo.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 让经过zuul的请求都必须带有token且不为空，才能被访问
 */
@Component
public class TokenFilter extends ZuulFilter {
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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //-1 获取当前上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //-2 获取到请求对象
        HttpServletRequest request = currentContext.getRequest();
        //-3 获取大token: 从参数里面获取。也可以从cookie/header里获取
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());//401
        }


        return null;
    }
}
