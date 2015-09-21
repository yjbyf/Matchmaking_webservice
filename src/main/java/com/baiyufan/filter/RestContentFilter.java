package com.baiyufan.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.baiyufan.filter.wrappers.BufferedHttpResponseWrapper;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class RestContentFilter implements Filter {

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
		HttpServletRequest req = (HttpServletRequest) request;
		String currentUserName = RequestUtils
				.getUserNameFromRequestAuthorization(req);
		// System.err.println(req.getMethod());
		// 请求数据只可能是get方法
		if (!"GET".equals(req.getMethod())) {
			chain.doFilter(request, response);
			return;
		}

		// 取当前用户id
		String currentUserId = RequestUtils
				.getUserIdFromRequestAuthorization(req);
		// System.err.println(currentUserId);
		BufferedHttpResponseWrapper responseWrapper = new BufferedHttpResponseWrapper(
				(HttpServletResponse) response);

		chain.doFilter(request, responseWrapper);

		String responseContent = new String(responseWrapper.getBuffer(),
				"UTF-8");
		//System.err.println("response content:" + responseContent);
		String requestUrl = req.getRequestURI();
		String paths[] = requestUrl.split("/");
		String path = "";
		if (paths != null && paths.length > 0) {
			path = paths[paths.length - 1];
		}
		// System.err.println(req.getRequestURI());

		String entityName = path;
		byte[] realResponse = responseContent.getBytes(Constants.UTF8);//默认是原来的输出值
		// System.err.println(entityName);
		try {
			List<Map<String, Object>> entities = JsonPath.read(responseContent,
					"$._embedded." + entityName);

			List<Map<String, Object>> entitiesToBeOutput = new ArrayList<Map<String, Object>>();
			//按权限过滤数据
			for (Map<String, Object> entity : entities) {
				// 当前用户id和记录上面的id比较
				String createdBy = (String) entity.get("createdBy");
				if (currentUserId.equals(createdBy) // 当前用户是创建者
						|| Constants.ADMIN.equals(currentUserName)) {// 超级管理员不需要控制权限
					entitiesToBeOutput.add(entity);
					// System.err.println("----" + createdBy);
					// System.err.println("----" + person.get("name"));
					// System.err.println("----" + person.get("pk"));
				}

			}
			// 对过滤后的数据进行格式化
			StringBuffer sb = new StringBuffer();

			for (Map<String, Object> person : entitiesToBeOutput) {
				JSONObject json = new JSONObject(person);
				sb.append(json.toString());
				sb.append(",");
				// System.err.println("----" + json.toString());
			}
			String strFromSb = "";
			if (sb.length() > 0) {
				strFromSb = sb.substring(0, sb.length() - 1);
			}
			strFromSb = "[" + strFromSb + "]";

			// System.err.println(strFromSb);

			realResponse = strFromSb.getBytes(Constants.UTF8);
		} catch (PathNotFoundException e) {
			
		}
		//输出内容到respone
		response.setContentLength(realResponse.length);
		response.getOutputStream().write(realResponse);
		// response.setContentLength(responseWrapper.getBufferSize());
		// response.getOutputStream().write(responseWrapper.getBuffer());
		response.flushBuffer();

	}
}
