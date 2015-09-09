package com.baiyufan;

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

@Component
public class SimpleCORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE,PATCH");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, accept, authorization");
		//return;
		
		HttpServletRequest request = (HttpServletRequest) req;

		String auth = request.getHeader("Authorization");

		System.err.println(auth);
		// TODO
		// 判断传送过来的用户名和密码是否符合数据中的
		// 1.符合，则
		if ("aa".equals(auth)) {
			chain.doFilter(req, res);
		}else{
			// 2.不符合，则do nothing
			//PrintWriter out = response.getWriter();
			//out.println("{}");
		}
		
		
		//chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
