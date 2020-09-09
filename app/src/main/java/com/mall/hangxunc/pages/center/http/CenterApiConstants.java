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
    String HOME_SEARCH = "/scocenter/#/pages/search/search?";
    String HOME_MESSAGE = "/scocenter/#/pages/message/message?";
    String HOME_ME = "/scocenter/#/pages/user/user?";
    //首页数据
    String HOME_DATA = "/hx-pc/appIndex/getIndex?";

    String IS_CUSTOMER_LOGIN = "/hx-pc/appUser/isCustomerLogin?";


    String HOME_SEARCH_DETAIL = "/hx-pc/tech/qyyfb/list?page=1&limit=4&queryValue=q&languageCode=zh-CN";

    //登录
    String LOGIN = "/scocenter/#/pages/login/login";

    String START_LOGIN = "/hx-pc/sys/user/login?username=mzhwct@163.com&password=123&languageCode=zh-CN";

    //手机号快速登录
    String START_PHONE_LOGIN = "/hx-pc/sys/user/yzm?mobile=17615865470&languageCode=zh-CN";

    //发布技术页面
    String PUBLISH_ACHIEVEMENT = "/scocenter/#/pages/achievement/publish-achievement";

    //发布需求页面链接:
    String PUBLISH_NEW_XQ = "/scocenter/#/pages/xqk/publishNewXq";
    //发布活动页面链接:
    String PUBLISH_NEW_ACT = "/scocenter/#/pages/activity/publishNewAct";

    //我的技术
    String GET_MY_TECHACHIEVEMENT = "/hx-pc/tech/techachievement/electTecAchListByUser?userId=1291626245571928066&checkstatus=2&languageCode=zh-CN";
    //我的需求
    String GET_MY_PUBXQ = "/hx-pc//tech/xqk/getMyPubXq/1291626245571928066?languageCode=zh-CN";
    //我的活动
    String GET_MY_PUBACT = "/hx-pc//swzx/type/getMyPubAct/1291626245571928066?languageCode=zh-CN";
    //需求活动
    String GET_MY_ACCEPTXQ = "/hx-pc//tech/xqk/getMyAcceptXq/1291626245571928066?languageCode=zh-CN";

    int LIMIT = 6;

}
