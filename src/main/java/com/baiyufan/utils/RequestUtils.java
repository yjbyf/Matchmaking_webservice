package com.baiyufan.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

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
	
	public static String getUserIdFromRequestAuthorization(HttpServletRequest request) {
		String[] authParts = getStringArrayFromRequestAuthorization(request);
		if (authParts == null) {
			return null;
		}
		String userId = authParts[2];
		return userId;
	}
	
	public static Integer getCreatedBy(HttpServletRequest request) {
		
		return new Integer(getUserIdFromRequestAuthorization(request));
	}

	public static String getStringFromRequest(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;

		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);// .append('\n');
			}
			return sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public static JSONUtils getJSONObjectFromRequest(HttpServletRequest request) {
		String jsonStr = getStringFromRequest(request);
		if (jsonStr != null && jsonStr.length() > 0) {
			JSONUtils json;
			try {
				json = new JSONUtils(jsonStr);
				return json;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;

	}

}
