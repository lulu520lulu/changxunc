package com.c.hangxunc.http;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ApiConstants {

    int LIMIT = 6;


    String IS_CUSTOMER_LOGIN = "/index.php?route=api/ioslink/isCustomerLogin";

    String BASE_URL = "http://c.hangxunc.com";
    String IMAGE_BASE_URL = "http://c.hangxunc.com/image";

    // 首页模块以及信息：分两部分
    String GET_HOME_TOP_PATH = "/index.php?route=api/ioslink/getHomeTop";

    //（包含全部商品模块，需要实现懒加载）
    String GET_HOME_BOTTOM_PATH = "/index.php?route=api/ioslink/getHomeBottom";

    //全部商品模块懒加载访问接口：（需要经过post方式传参count和limit（limit可以直接定义6））
    String GET_ALL_PRODUCT_MOBILE_PATH = "/index.php?route=extension/module/all_product_mobile/iosadd";
    //添加购物车
    String ADD_SHOP_CART_PATH = "/index.php?route=checkout/cart/iosAdd";
    //首页分类数据接口：
    String GET_CATEGORY_PATH = "/index.php?route=api/ioslink/getCategory";

    String CATEGORY_DETAIL_PATH = "/index.php?route=product/category&path=";

    //根据id获得产品信息：（参数不要加引号等内容）
    String GET_PRODUCT_PATH = "/index.php?route=api/ioslink/getProduct";

    //搜索页数据接口：（参数不要加引号等内容，搜索出来的结果在products字段中）
    String SEARCH_PRO_PATH = "/index.php?route=api/ioslink/getSearchInfo";

    //分类页-分类数据接口：
    String GET_CATEGORY_PAGE_PATH = "/index.php?route=api/ioslink/getCategoryPage";


    //分类页-品牌数据接口：
    String GET_MANUFACTURER_PAGE_PATH = "index.php?route=api/ioslink/getManufacturer";

    //语言切换：
    String GET_LANGUAGE_PATH = "/index.php?route=api/ioslink/getLanguage";

    // 货币切换：
    String GET_CURRENCY_PATH = "/index.php?route=api/ioslink/getCurrency";

    //获得版权信息：
    String GET_COPY_RIGHT_PATH = "/index.php?route=api/ioslink/getCopyRight";

    //注册支持国家接口：
    String GET_COUNTRY_PATH = "/index.php?route=api/ioslink/getCountry";

    //--------------登录 接口--------------

    String SMS_CODE_PATH = "/index.php?route=account/register/smsCode";

    /**
     * 必需参数：【post方法传递】——email/telephone字段必须有一个不为空
     *
     *     type（只能取mobile和email——分别是两种登录方式，邮箱登录和手机号登录）
     *     calling_code（数据来自“注册支持国家接口”查出来的数据中“code”字段的值）
     *     telephone
     *     email
     *     password（无需加密）
     */

    /**
     * 其中，若登录失败则返回错误原因字符串（unicode码），登录成功则返回需要存入session的data字段中的数据信息（包含customer_id、shipping_method和payment_method）
     * 原系统登录成功后跳转页面地址为 b.hangxunc.com/index.php?route=account/account
     */
    //访问地址：
    String LOGIN_PATH = "/index.php?route=account/login/ioslogin";


    //--------------注册接口--------------
    /**
     *  必需参数：【post方法传递】——email/telephone字段必须有一个不为空
     *
     *     customer_group_id = 1（默认用户组编号，固定值为1即可）
     *     type（只能取mobile和email——分别是两种登录方式，邮箱登录和手机号登录）
     *     email
     *     calling_code（数据来自“注册支持国家接口”查出来的数据中“code”字段的值）
     *     telephone
     *     smscode（验证码，原项目暂未配置短信验证码服务，接口待开放）
     *     password
     *     confirm（确认密码）
     *     newsletter（是否开放消息订阅，checkbox框，可选可不选）
     *     agree（是否同意用户协议，同意协议则有该字段，值为1，不同意则没有此字段，若不同意则无法通过注册）
     *
     */
    /**
     * 其中，若登录失败则返回错误原因字符串（unicode码），登录成功则返回需要存入success字符串，此时数据库已添加该用户信息，可以正常登录
     * 原系统登录成功后跳转页面地址为 b.hangxunc.com/index.php?route=account/account
     */
    //访问地址：
    String REGIST_PATH = "/index.php?route=account/register/iosregist";

    String LOGIN_MOBILE_TYPE = "mobile";
    String LOGIN_EMAIL_TYPE = "email";
    String RULE_PATH = "http://c.hangxunc.com/index.php?route=information/information/agree&information_id=3";

    @StringDef({LOGIN_MOBILE_TYPE, LOGIN_EMAIL_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface LOGIN_TYPE {

    }

    //--------------WebUrl--------------
    //商品详情页：（参数不要加引号等内容）
    String PRODUCT_PAGE_PATH = "/index.php?route=product/product&product_id=";
    String CUSTOMER_ID_PATH = "&customer_id=";
    String LANGUAGE_PATH = "&language=";
    String CURRENCY_PATH = "&currency=";

    //会员中心的页面跳转

    String ACCOUNT_PAGE_PATH = "/index.php?route=account/account&customer_id=";

    //购物车
    String CART_PAGE_PATH = "/index.php?route=checkout/cart&customer_id=";

    //忘记密码页面
    String FORGOTTEN_PAGE_PATH = "/index.php?route=account/forgotten";


}
