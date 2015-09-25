package com.baiyufan.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Configurable;

import com.baiyufan.config.ApplicationContextHolder;
import com.baiyufan.db.model.TUser;
import com.baiyufan.db.persistence.TUserMapper;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;

//@Component
@Configurable
public class RestSecurityFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// System.err.println("filter--->RestSecurityFilter");
		HttpServletRequest request = (HttpServletRequest) req;

		String username = RequestUtils
				.getUserNameFromRequestAuthorization(request);
		String password = RequestUtils
				.getPasswordFromRequestAuthorization(request);

		// String userId =
		// RequestUtils.getUserIdFromRequestAuthorization(request);
		// System.err.println(userId);
		if (username == null || password == null) {
			return;
		}

		if (validUser(username, password)) {// 校验用户名和密码是否一致
			// 只有admin才能访问user资源
			String requestUrl = request.getRequestURI();
			// System.err.println(requestUrl);
			// 只有admin才能访问/user/*和/employee/*下的资源
			if (requestUrl
					.startsWith(Constants.USER_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH)) {
				if (!Constants.ADMIN.equals(username)) {
					return;
				}
			}
			chain.doFilter(req, res);
		}

	}

	// private boolean validUser(String username, String password) {
	// UserRepository repository = ApplicationContextHolder.getContext()
	// .getBean(UserRepository.class);
	// for (User user : repository.findByUserName(username)) {
	// if (password.equals(user.getPassword())) {
	// return true;
	// }
	// }
	// return false;
	// }

	private boolean validUser(String username, String password) {
		TUserMapper userMapper = ApplicationContextHolder.getContext().getBean(
				TUserMapper.class);
		TUser param = new TUser();
		param.setUserName(username);
		param.setAliveFlag(Constants.VALID_FLAG);
		List<TUser> userList = userMapper.selectClause(param);
		if (userList != null) {
			for (TUser user : userList) {
				if (password.equals(user.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
