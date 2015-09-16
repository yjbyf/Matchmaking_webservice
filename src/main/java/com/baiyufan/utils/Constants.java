package com.baiyufan.utils;

public class Constants {
	public final static String SLASH = "/";
	
	public final static String LOGIN_VALID="loginValid";
	
	public final static String LOGIN_VALID_PRE_WITH_SLASH = SLASH + LOGIN_VALID;
	
	public final static String CHANGE_OWN_PASSWORD = SLASH + "change_own_password";
	
	public final static String USER_REST_WEBSERVICE_PATH = "user";
		
	public final static String USER_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH = SLASH + USER_REST_WEBSERVICE_PATH;
	
	//public final static String GET_USER_LIST_WITH_PRIV = SLASH + "userWithPriv";
	
	public final static String USER_REST_WEBSERVICE_COUNT = USER_REST_WEBSERVICE_PATH + SLASH + "count";
	
	public final static String USER_REST_WEBSERVICE_CHANGE_PASSWORD = USER_REST_WEBSERVICE_PATH + SLASH + "changePassword";
	
	public final static String PERSON_REST_WEBSERVICE_PATH = "person";
	
	public final static String CONTACT_REST_WEBSERVICE_PATH = "contact";
	
	public final static String EMPLOYEE_REST_WEBSERVICE_PATH = "employee";
	
	public final static String EMPLOYEE_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH = SLASH + EMPLOYEE_REST_WEBSERVICE_PATH;
	
	public final static String ADMIN = "admin";
	
	public final static String ADMIN_INIT_PASSWORD = "21232f297a57a5a743894a0e4a801fc3";
	
	public final static String JSON_RESULT_SUCESS = "{\"result\":\"success\"}";
	public final static String JSON_RESULT_FAILED = "{\"result\":\"failed\"}";
}
