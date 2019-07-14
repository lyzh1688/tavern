package com.tuoyou.tavern.gateway.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CrossDomainFilter implements Filter {
	private boolean isCross = true;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		String isCrossStr = filterConfig.getInitParameter("IsCross");
//		isCross = isCrossStr.equals("true") ? true : false;
	}

	@Override
	public void destroy() {
		isCross = false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 说明是跨域请求
		if (isCross) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			String origin = httpServletRequest.getHeader("origin");// 获取源站
			httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
			httpServletResponse.setHeader("Access-Control-Max-Age","1800");//默认1800s
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");//开启cookie读写
			httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH,OPTIONS");
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
			httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP1.1.
			httpServletResponse.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0.
		}
		chain.doFilter(request, response);
	}
}