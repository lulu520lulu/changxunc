package com.c.hangxunc.http;

import com.c.hangxunc.bean.home.BaseBean;
import com.c.hangxunc.bean.home.CategoryListData;
import com.c.hangxunc.bean.home.CountryListData;
import com.c.hangxunc.bean.home.CurrencyListData;
import com.c.hangxunc.bean.home.HomeCategoryData;
import com.c.hangxunc.bean.home.LanguageListData;
import com.c.hangxunc.bean.home.ModulesListData;
import com.c.hangxunc.bean.home.IsLoginBean;
import com.c.hangxunc.bean.home.ProductBean;
import com.c.hangxunc.bean.home.ProductListBean;
import com.c.hangxunc.bean.home.SearchResultBean;
import com.c.hangxunc.bean.home.SearchResultData;
import com.c.hangxunc.bean.home.SmsCodeBean;
import com.c.hangxunc.bean.login.LoginData;
import com.c.hangxunc.bean.login.LoginInfo;
import com.c.hangxunc.bean.login.RegistInfo;
import com.c.hangxunc.bean.login.SmsCodeData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HangXunCService {


    @GET(ApiConstants.GET_HOME_TOP_PATH)
    Call<ModulesListData> getHomeTop(@Query("sco") String custom_id, @Query("language") String language, @Query("currency") String currency);

//    @GET(ApiConstants.GET_HOME_BOTTOM_PATH)
//    Call<ProductListBean> getHomeBottom(@Query("sco") String custom_id, @Query("language") String language, @Query("currency") String currency);

    /**
     * 全部商品模块
     *
     * @param count
     * @param limit
     * @return
     */
    @POST(ApiConstants.GET_ALL_PRODUCT_MOBILE_PATH)
    @FormUrlEncoded
    Call<ProductListBean> getAllProduct(@Field("sco") String custom_id, @Field("count") int count,
                                        @Field("limit") int limit, @Field("language") String language, @Field("currency") String currency);

    /**
     * 首页添加购物车
     *
     * @return
     */
    @POST(ApiConstants.ADD_SHOP_CART_PATH)
    @FormUrlEncoded
    Call<BaseBean> addShopCart(@Field("sco") String sco, @Field("session_id") String session_id,
                               @Field("product_id") int product_id, @Field("quantity") int quantity);

    /**
     * 首页分类
     *
     * @return
     */
    @GET(ApiConstants.GET_CATEGORY_PATH)
    Call<HomeCategoryData> getCategory(@Query("sco") String sco, @Query("language") String language, @Query("currency") String currency);

//    /**
//     * 根据id获得产品
//     *
//     * @return
//     */
//    @GET(ApiConstants.GET_PRODUCT_PATH)
//    Call<ProductBean> getProduct(@Query("sco") String sco, @Query("id") int id, @Query("language") String language, @Query("currency") String currency);


    /**
     * 搜索页数据
     *
     * @return
     */
    @GET(ApiConstants.SEARCH_PRO_PATH)
    Call<SearchResultData> searchPro(@Query("sco") String sco, @Query("search") String keyWord,
                                     @Query("sort") String sort, @Query("order") String order, @Query("language") String language, @Query("currency") String currency);

    /**
//     * 搜索页数据
//     *
//     * @return
//     */
//    @GET(ApiConstants.SEARCH_PRO_PATH)
//    Call<BreadCrumbsBean> searchPro(@Query("sco") String sco, @Query("search") String keyWord, @Query("language") String language, @Query("currency") String currency);

    /**
     * 分类页--分类数据
     *
     * @return
     */
    @GET(ApiConstants.GET_CATEGORY_PAGE_PATH)
    Call<CategoryListData> getCategoryPage(@Query("sco") String sco, @Query("language") String language, @Query("currency") String currency);


    /**
     * 分类页--品牌数据
     *
     * @return
     */
    @GET(ApiConstants.GET_MANUFACTURER_PAGE_PATH)
    Call<CategoryListData> getManufacturer(@Query("sco") String sco, @Query("language") String language, @Query("currency") String currency);

    /**
     * 语言切换
     */
    @GET(ApiConstants.GET_LANGUAGE_PATH)
    Call<LanguageListData> getLanguage(@Query("sco") String sco);

    /**
     * 货币切换
     */
    @GET(ApiConstants.GET_CURRENCY_PATH)
    Call<CurrencyListData> getCurrency(@Query("sco") String sco);


    /**
     * 语言切换
     */
    @GET(ApiConstants.GET_LANGUAGE_PATH)
    Call<LanguageListData> getLanguage(@Query("sco") String sco, @Query("language") String language);

    /**
     * 货币切换
     */
    @GET(ApiConstants.GET_CURRENCY_PATH)
    Call<CurrencyListData> getCurrency(@Query("sco") String sco, @Query("currency") String currency);

//    /**
//     * 获取版权信息
//     */
//    @GET(ApiConstants.GET_COPY_RIGHT_PATH)
//    Call<LanguageBean> getCopyRight();

    /**
     * 注册支持国家
     */
    @GET(ApiConstants.GET_COUNTRY_PATH)
    Call<CountryListData> getCountry(@Query("sco") String sco, @Query("language") String language, @Query("currency") String currency);

    /**
     * 登录
     *
     * @param type
     * @param callingCode
     * @param telephone
     * @param email
     * @param password
     * @return
     */
    @POST(ApiConstants.LOGIN_PATH)
    @FormUrlEncoded
    Call<LoginData> login(
            @Field("type") String type, @Field("calling_code") String callingCode,
            @Field("telephone") String telephone, @Field("email") String email,
            @Field("password") String password, @Query("session_id") String session_id);

    /**
     * regist
     *
     * @param groupId
     * @param type
     * @param email
     * @param callingCode
     * @param telephone
     * @param smsCode
     * @param password
     * @param confirm
     * @param newsletter
     * @param agree
     * @return
     */
    @POST(ApiConstants.REGIST_PATH)
    @FormUrlEncoded
    Call<RegistInfo> regist(
            @Field("customer_group_id") int groupId, @Field("type") String type,
            @Field("email") String email, @Field("calling_code") String callingCode,
            @Field("telephone") String telephone, @Field("smscode") String smsCode,
            @Field("password") String password, @Field("confirm") String confirm,
            @Field("newsletter") String newsletter, @Field("agree") String agree);

    /**
     * SMS CODE
     *
     * @param calling_code
     * @param telephone
     * @return
     */
    @POST(ApiConstants.SMS_CODE_PATH)
    @FormUrlEncoded
    Call<SmsCodeData> smsCode(@Field("calling_code") String calling_code, @Field("telephone") String telephone);

    /**
     * 检查登录状态
     */
    @GET(ApiConstants.IS_CUSTOMER_LOGIN)
    Call<IsLoginBean> isCustomerLogin(@Query("sco") String sco, @Query("session_id") String session_id);


}