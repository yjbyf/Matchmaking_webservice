package com.baiyufan.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.baiyufan.utils.Constants;

//@Component
public class RestSecurityFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		//System.err.println("filter--->RestSecurityFilter");
		HttpServletRequest request = (HttpServletRequest) req;
		String accessUrl = request.getRequestURI();//访问路径，用来安全认证用
		if(accessUrl==null){
			accessUrl ="";
		}
		
		String auth = request.getHeader("Authorization");
		//System.err.println("auth:"+auth);
		//无安全认证则直接返回空页面，不继续进行rest webservice操作
		if(auth==null){
			return;
		}
		chain.doFilter(req, res);
		
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
