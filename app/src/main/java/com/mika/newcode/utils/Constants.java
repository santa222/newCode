package com.mika.newcode.utils;

public class Constants {

    //Network Helper Constants
    public static final String URL_PATH = "http://10.120.30.154/bems/bems_api.php";
    public static final String MOBILE_USERS_URL_PATH = "mobile_users/";
    public static final String PATH_SIGN_IN = MOBILE_USERS_URL_PATH + "sign_in";
    public static final String PATH_GUEST = MOBILE_USERS_URL_PATH + "sign_in_guest";
    public static final String PATH_SIGN_OUT = MOBILE_USERS_URL_PATH + "sign_out";
    public static final String PATH_SIGN_UP = MOBILE_USERS_URL_PATH + "sign_up";
    public static final String PATH_GET_SMS_VERIFICATION = MOBILE_USERS_URL_PATH + "send_message";
    public static final String PATH_VERIFICATION = MOBILE_USERS_URL_PATH + "verify";
    public static final String NULL = "null";
    public static final String TECHNOLOGY = "android";
    public static final int CONNECTION_TIMEOUT = 30000;
    public static final int SO_TIMEOUT = 30000;


    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final int NEW_MSG_EVENT_ID = 1000;
    public static final String MESSAGE_KEY = "message";
}