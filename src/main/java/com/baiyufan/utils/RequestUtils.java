package com.baiyufan.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	public static String[] getStringArrayFromRequestAuthorization(
			HttpServletRequest request) {
		String auth = request.getHeader("Authorization");
		// System.err.println("auth:"+auth);
		// 无安全认证则直接返回空页面，不继续进行rest webservice操作
		if (auth == null) {
			return null;
		} else {
			// 有安全认证，则校验用户名和密码是否一致
			// System.err.println(auth);
			if (auth.indexOf(",") < 0) {
				return null;
			}
			String[] authParts = auth.split(",");
			if (authParts.length < 2) {
				return null;
			}
			return authParts;
		}
	}

	public static String getUserNameFromRequestAuthorization(
			HttpServletRequest request) {
		String[] authParts = getStringArrayFromRequestAuthorization(request);
		if (authParts == null) {
			return null;
		}
		String username = authParts[0];
		return username;
	}
	
	public static String getPasswordFromRequestAuthorization(
			HttpServletRequest request) {
		String[] authParts = getStringArrayFromRequestAuthorization(request);
		if (authParts == null) {
			return null;
		}
		String password = authParts[1];
		return password;
	}

}
