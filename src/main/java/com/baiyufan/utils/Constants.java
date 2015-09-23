package com.baiyufan.utils;

public class Constants {
	//common setting
	public final static String UTF8 = "UTF8";
	public final static String NULL_STRING = "";
	public final static String NEW = "new";
	public final static String MOD = "mod";
	// ///////////////////////////////////////////////////////////////////////////////////////////////////
	// database fields
	public final static String VALID_FLAG = "1";
	public final static String INVALID_FLAG = "0";
	public final static String ALIVE_FLAG = "aliveFlag";
	public final static String ID = "id";
	public final static String PK = "pk";

	// ///////////////////////////////////////////////////////////////////////////////////////////////
	// url path
	public final static String SLASH = "/";

	public final static String LOGIN_VALID = "loginValid";

	public final static String URL_NOPRIV = "open";
	public final static String URL_PRIV = "rest";
	//no privilage url
	public final static String LOGIN_VALID_PRE_WITH_SLASH = SLASH + URL_NOPRIV
			+ SLASH + LOGIN_VALID;

	public final static String CHANGE_OWN_PASSWORD = SLASH + URL_NOPRIV + SLASH
			+ "change_own_password";
	
	public final static String USER_REST_WEBSERVICE_NO_PRIV = SLASH
			+ URL_NOPRIV + SLASH + "userListNoPriv";
	
	//user
	public final static String USER_REST_WEBSERVICE_PATH = "user";

	public final static String USER_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH = SLASH
			+ URL_PRIV + SLASH + USER_REST_WEBSERVICE_PATH;

	public final static String USER_REST_WEBSERVICE_COUNT = SLASH + URL_PRIV
			+ SLASH + USER_REST_WEBSERVICE_PATH + SLASH + "count";

	public final static String USER_REST_WEBSERVICE_CHANGE_PASSWORD = USER_REST_WEBSERVICE_PATH
			+ SLASH + "changePassword";
	// person
	public final static String PERSON_REST_WEBSERVICE_PATH = "person";

	public final static String CONTACT_REST_WEBSERVICE_PATH = "contact";

	// contract
	public final static String CONTRACT_REST_WEBSERVICE_PATH = "contract";

	public final static String CONTRACT_SAVE_REST_WEBSERVICE_PATH = SLASH
			+ URL_PRIV + SLASH + CONTRACT_REST_WEBSERVICE_PATH + "/save";

	public final static String CONTRACT_VALID_REST_WEBSERVICE_PATH = SLASH
			+ URL_PRIV + SLASH + CONTRACT_REST_WEBSERVICE_PATH + "/valid";
	// employee
	public final static String EMPLOYEE_REST_WEBSERVICE_PATH = "employee";

	public final static String EMPLOYEE_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH = SLASH
			+ EMPLOYEE_REST_WEBSERVICE_PATH;
	
	//match
	public final static String MATCH_REST_WEBSERVICE_PATH = "match";
	
	public final static String MATCH_NEW_REST_WEBSERVICE_PATH = SLASH
			+ URL_PRIV + SLASH + MATCH_REST_WEBSERVICE_PATH + "/new";
	
	public final static String MATCH_MOD_REST_WEBSERVICE_PATH = SLASH
			+ URL_PRIV + SLASH + MATCH_REST_WEBSERVICE_PATH + "/mod";
	
	public final static String MATCH_DEL_REST_WEBSERVICE_PATH = SLASH
			+ URL_PRIV + SLASH + MATCH_REST_WEBSERVICE_PATH + "/del";

	// ///////////////////////////////////////////////////////////////////////////////////////////////////
	// admin init password
	public final static String ADMIN = "admin";

	public final static String ADMIN_INIT_PASSWORD = "21232f297a57a5a743894a0e4a801fc3";
	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	// json result for response
	public final static String JSON_RESULT_SUCESS = "{\"result\":\"success\"}";
	public final static String JSON_RESULT_FAILED = "{\"result\":\"failed\"}";
}
