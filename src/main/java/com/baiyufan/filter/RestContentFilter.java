package com.baiyufan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.server.ServletServerHttpResponse;

import com.baiyufan.filter.wrappers.BufferedHttpResponseWrapper;

public class RestContentFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		BufferedHttpResponseWrapper responseWrapper =
                new BufferedHttpResponseWrapper((HttpServletResponse) response);
		
		chain.doFilter(request, responseWrapper);
		String responseContent = new String(responseWrapper.getBuffer(),"UTF-8");
		System.err.println("response content:"+responseContent);
		
		response.setContentLength(responseWrapper.getBufferSize());
        //res.setContentType("text/html");
		response.getOutputStream().write(responseWrapper.getBuffer());
        response.flushBuffer( );
	}

	
	
	

}
