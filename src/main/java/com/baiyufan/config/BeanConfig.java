package com.baiyufan.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baiyufan.filter.RestContentFilter;
import com.baiyufan.filter.RestSecurityFilter;
import com.baiyufan.filter.SimpleCORSFilter;
import com.baiyufan.utils.Constants;

@Configuration
public class BeanConfig {
	// @Autowired
	// private Filter securityFilter;
	// 定义filter的优先级
	// 跨域提交控制
	@Bean
	public FilterRegistrationBean corsFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		SimpleCORSFilter simpleCORSFilter = new SimpleCORSFilter();
		registrationBean.setFilter(simpleCORSFilter);
		registrationBean.setOrder(1);
		return registrationBean;
	}

	// rest 访问安全
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		RestSecurityFilter securityFilter = new RestSecurityFilter();
		registrationBean.setFilter(securityFilter);
		registrationBean.addUrlPatterns("/" + Constants.URL_PRIV + "/*");
		// registrationBean.addUrlPatterns("/"+Constants.USER_REST_WEBSERVICE_PATH+"/*");
		// registrationBean.addUrlPatterns("/"+Constants.PERSON_REST_WEBSERVICE_PATH+"/*");
		// registrationBean.addUrlPatterns("/"+Constants.EMPLOYEE_REST_WEBSERVICE_PATH+"/*");
		// registrationBean.addUrlPatterns("/"+Constants.CONTRACT_REST_WEBSERVICE_PATH+"/*");
		registrationBean.setOrder(2);
		return registrationBean;
	}

	// 请求资源数据内容按照人员过滤
	@Bean
	public FilterRegistrationBean restContentFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		RestContentFilter restContentFilter = new RestContentFilter();
		registrationBean.setFilter(restContentFilter);
		registrationBean.addUrlPatterns("/" + Constants.URL_PRIV + "/*");
		registrationBean.addUrlPatterns("/test/*");//test目录下存放测试用例
		registrationBean.setOrder(3);
		return registrationBean;
	}

}
