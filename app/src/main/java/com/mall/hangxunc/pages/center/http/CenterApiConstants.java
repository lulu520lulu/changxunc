package com.mall.hangxunc.pages.center.http;

public interface CenterApiConstants {

    String LOGIN_MOBILE_TYPE = "mobile";
    String LOGIN_EMAIL_TYPE = "email";
    String CUSTOMER_ID_PATH = "&sco=";
    String LANGUAGE_PATH = "&language=";
    String CURRENCY_PATH = "&currency=";
    String BASE_URL = "http://d.hangxunc.com:8081";
    String APP = "&app=app";
    //H5
    String HOME_SEARCH = "/scocenter/#/pages/search/search?&sco=";
    String HOME_MESSAGE = "/scocenter/#/pages/message/message?&sco=";
    String HOME_ME = "/scocenter/#/pages/user/user?&sco=";
    //首页数据
    String HOME_DATA = "/hx-pc/appIndex/getIndex?";

    String IS_CUSTOMER_LOGIN = "/hx-pc/appUser/isCustomerLogin?";

    String GET_ALL_INDUSTY = "/hx-pc/appIndusty/getAllIndusty?languageCode=zh-CN";

    int LIMIT = 6;

}
