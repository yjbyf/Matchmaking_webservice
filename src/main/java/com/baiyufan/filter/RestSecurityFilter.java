package com.baiyufan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Configurable;

import com.baiyufan.config.ApplicationContextHolder;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;

//@Component
@Configurable
public class RestSecurityFilter implements Filter {
		
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		//System.err.println("filter--->RestSecurityFilter");
		HttpServletRequest request = (HttpServletRequest) req;
				
		String auth = request.getHeader("Authorization");
		//System.err.println("auth:"+auth);
		//无安全认证则直接返回空页面，不继续进行rest webservice操作
		if(auth==null){
			return;
		}else{
			//有安全认证，则校验用户名和密码是否一致
			//System.err.println(auth);
			if(auth.indexOf(",")<0){
				return;
			}
			String[] autuParts = auth.split(",");
			if(autuParts.length<2){
				return;
			}
			String username = autuParts[0];
			String password = autuParts[1];
			if(validUser(username,password)){//校验用户名和密码是否一致
				//只有admin才能访问user资源
				String requestUrl = request.getRequestURI();
				//System.err.println(requestUrl);
				//只有admin才能访问/user/*下的资源
				if(requestUrl.startsWith(Constants.USER_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH)){
					if(!Constants.ADMIN.equals(username)){
						return;
					}
				}
				chain.doFilter(req, res);
			}			
		}
	}
	
	private boolean validUser(String username,String password){		
		UserRepository repository = ApplicationContextHolder.getContext().getBean(UserRepository.class);
		for (User user : repository.findByUserName(username)) {
			if(password.equals(user.getPassword())){
				//System.err.println(auth+" pass check");
				return true;
			}
			//System.out.println(customer);
		}
		return false;
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
