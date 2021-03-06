package com.tuoyou.tavern.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tuoyou.tavern.auth.libs.AuthTokenFactor;
import com.tuoyou.tavern.auth.libs.JwtAuthenticationProperty;
import com.tuoyou.tavern.auth.libs.TokenHelper;
import com.tuoyou.tavern.protocol.authcenter.reponse.LoginResponse;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
public class DoAfterLogin extends ZuulFilter {

    @Autowired
    private JwtAuthenticationProperty config;

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        //从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        if (uri.equals(config.getUrl())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object run() {
        LoginResponse loginResponse = null;
        RequestContext ctx = RequestContext.getCurrentContext();
        InputStream stream = ctx.getResponseDataStream();
        //解析登陆返回结果
        try {
            String body = IOUtils.toString(stream, Charset.forName("utf-8"));
            loginResponse = JSON.parseObject(body).toJavaObject(LoginResponse.class);
            if (loginResponse.getRetCode() != 0) {
                ctx.setResponseBody(JSON.toJSONString(new TavernResponse(RetCode.AUTH_FAILED, loginResponse.getRetMessage())));
                ctx.set(ContextDict.isLoginSuccess, false);
                return null;
            }
            //登陆成功
            AuthTokenFactor authTokenFactor = new AuthTokenFactor();
//            authTokenFactor.setUserType(loginResponse.getData().getUserType());
            authTokenFactor.setUserAccnt(loginResponse.getData().getUserAccnt());
            authTokenFactor.setRoleId(loginResponse.getData().getRoles());
            String token = TokenHelper.createToken(this.config, authTokenFactor);
            loginResponse.getData().setToken(token);
            RequestContext.getCurrentContext().setResponseBody(JSON.toJSONString(loginResponse));
        } catch (Exception e) {
            ctx.setResponseBody(JSON.toJSONString(new TavernResponse(RetCode.AUTH_FAILED, "登陆失败")));
            ctx.set(ContextDict.isLoginSuccess, false);
            return null;
        }
        //登陆异常
        if (loginResponse == null) {
            ctx.set(ContextDict.isLoginSuccess, false);
            return null;
        }
        //登陆失败
        if (!loginResponse.getData().isLoginSuccess()) {
            return null;
        }
        return null;
    }
}
