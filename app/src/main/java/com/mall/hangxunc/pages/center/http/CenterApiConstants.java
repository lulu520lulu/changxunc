package com.mall.hangxunc.pages.center.http;

public interface CenterApiConstants {

    String LOGIN_MOBILE_TYPE = "mobile";
    String LOGIN_EMAIL_TYPE = "email";
    String CUSTOMER_ID_PATH = "&sco=";
    String LANGUAGE_PATH = "&language=";
    String CURRENCY_PATH = "&currency=";
    String BASE_URL = "http://d.hangxunc.com:8081";
    String APP = "&app=app";



    String HOME_SEARCH = "/scocenter/#/pages/search/search";
    String HOME_MESSAGE = "/scocenter/#/pages/message/message";
    String HOME_ME = "/scocenter/#/pages/user/user";

    //首页数据
    String HOME_DATA = "/hx-pc/appIndex/getIndex?";


    //1、成果推广接口
    String HOME_TECHACHIEVEMENT = "/hx-pc/tech/techachievement/listPage?page=1&limit=4&languageCode=zh-CN";

    //成果详情页面链接
    String HOME_TECHACHIEVEMENT_DETAIL = "/scocenter/#/pages/achievement/achievement-info?id=";

    //2、大型仪器接口大型仪器:code=GYGCSB；研发设备:code=YFSB
    String HOME_INSTRUMENTSORT_GYGCSB = "/hx-pc/tech/instrumentsort/getByCode?code=GYGCSB&languageCode=zh-CN";
    String HOME_INSTRUMENTSORT_YFSB = "/hx-pc/tech/instrumentsort/getByCode?code=YFSB&languageCode=zh-CN";

    //大型仪器详情页面链接
    String HOME_INSTRUMENTSORT_DETAIL = "/scocenter/#/pages/instrument/instrumentDetails?id=";

    //3、最新技术需求
    String HOME_NEW_XQK_LIST = "/hx-pc/tech/xqk/list?page=1&limit=4&languageCode=zh-CN";

    //最新技术详情页面链接
    String HOME_NEW_LIST_XQK_DETAIL = "/scocenter/#/pages/xqk/xqkDetail?id=";

    //4、最新活动
    String HOME_NEW_SWZX_LIST = "/hx-pc/swzx/type/list?page=1&limit=4&languageCode=zh-CN";

    //最新活动详情页面链接
    String HOME_NEW_SWZX_LIST_DETAIL = "/scocenter/#/pages/activity/activityDetails?id=";

    //5、上合热点
    String HOME_LISTNEWS = "/hx-pc/sys/sysnews/listNews?page=1&limit=4&languageCode=zh-CN";

    //上合热点详情页面链接:
    String HOME_LISTNEWS_DETAIL = "/scocenter/#/pages/details/details?id=";


    String HOME_SEARCH_DETAIL = "/hx-pc/tech/qyyfb/list?page=1&limit=4&queryValue=q&languageCode=zh-CN";

    //登录
    String LOGIN = "/scocenter/#/pages/login/login";

    String START_LOGIN = "/hx-pc/sys/user/login?username=mzhwct@163.com&password=123&languageCode=zh-CN";

    //手机号快速登录
    String START_PHONE_LOGIN = "/hx-pc/sys/user/yzm?mobile=17615865470&languageCode=zh-CN";

    //判断用户是否登录
    String IS_CUSTOMER_LOGIN = "http://192.168.1.119:8081/hx-pc/appUser/isCustomerLogin?sco=TnpjPQ==";


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
