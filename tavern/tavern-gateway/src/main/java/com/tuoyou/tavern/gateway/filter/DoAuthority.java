package com.tuoyou.tavern.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tuoyou.tavern.auth.libs.AuthTokenFactor;
import com.tuoyou.tavern.auth.libs.JwtAuthenticationProperty;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernRequestAuthFields;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by 刘悦之 on 2019/7/6.
 */
public class DoAuthority extends ZuulFilter {

    @Autowired
    private JwtAuthenticationProperty config;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        if(uri.equals(config.getUrl())){
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        //从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader(config.getHeader());
        if (token != null && token.startsWith(config.getPrefix() + " ")) {
            token = token.replace(config.getPrefix() + " ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(config.getSecret().getBytes())
                        .parseClaimsJws(token)
                        .getBody();
                if(claims == null){
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseBody(JSON.toJSONString(new TavernResponse(RetCode.AUTH_FAILED,"非法的TOKEN")));
                    ctx.set(ContextDict.isLoginSuccess, false);
                }
                else {
                    ctx.set(ContextDict.isLoginSuccess, true);
                    ctx.addZuulRequestHeader(TavernRequestAuthFields.USER_ACCNT,claims.get(AuthTokenFactor.Factor.USER_ACCNT,String.class));
                    ctx.addZuulRequestHeader(TavernRequestAuthFields.USER_TYPE,claims.get(AuthTokenFactor.Factor.USER_TYPE,String.class));
                }
            } catch (Exception ignore) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody(JSON.toJSONString(new TavernResponse(RetCode.AUTH_FAILED,"非法的TOKEN")));
                ctx.set(ContextDict.isLoginSuccess, false);
            }
        }
        return null;
    }
}
