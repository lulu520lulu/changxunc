package com.c.hangxunc.pages.street.http;


import com.c.hangxunc.bean.home.BaseBean;
import com.c.hangxunc.bean.home.CategoryListData;
import com.c.hangxunc.bean.home.CountryListData;
import com.c.hangxunc.bean.home.CurrencyListBean;
import com.c.hangxunc.bean.home.HomeCategoryData;
import com.c.hangxunc.bean.home.IsLoginBean;
import com.c.hangxunc.bean.home.LanguageListData;
import com.c.hangxunc.bean.home.ModulesListData;
import com.c.hangxunc.bean.home.ProductListBean;
import com.c.hangxunc.bean.home.SearchResultData;
import com.c.hangxunc.bean.login.LoginData;
import com.c.hangxunc.bean.login.RegistInfo;
import com.c.hangxunc.bean.login.SmsCodeData;
import com.c.hangxunc.utils.CurrencySp;
import com.c.hangxunc.utils.LanguageSp;
import com.c.hangxunc.utils.LoginUtils;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StreetHangXunBiz {

    private static final String TAG = StreetHangXunBiz.class.getSimpleName();

    private Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new StreetLocalCookieJar())
//                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
//                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(StreetApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }


    private Retrofit getPostRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new StreetParamInterceptor())
                .cookieJar(new StreetLocalCookieJar())
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(StreetApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    private StreetHangXunBiz() {
    }

    private static class Singleton {
        private static StreetHangXunBiz instance = new StreetHangXunBiz();
    }

    public static StreetHangXunBiz getInstance() {
        return Singleton.instance;
    }


    public void getHomeTop(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<ModulesListData> call = service.getHomeTop(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

//    public void getHomeBottom(ResponseListener listener) {
//        HangXunCService service = getRetrofit().create(HangXunCService.class);
//        Call<ProductListBean> call = service.getHomeBottom(LoginUtils.getInstance().getScoId(), LanguageSp.getInstance().getCode()
//                , CurrencySp.getInstance().getCode());
//        call.enqueue(listener);
//    }

    /**
     * 全部商品模块
     *
     * @param count
     * @return
     */
    public void getAllProduct(int count, StreetResponseListener listener) {
        StreetHangXunCService service = getPostRetrofit().create(StreetHangXunCService.class);
        Call<ProductListBean> call = service.getAllProduct(LoginUtils.getInstance().getScoId(), count, StreetApiConstants.LIMIT,
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    public void addShopCart(int product_id, int quantity, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<BaseBean> call = service.addShopCart(
                LoginUtils.getInstance().getScoId(),
                LoginUtils.getInstance().getSession(), product_id, quantity);
        call.enqueue(listener);
    }

    /**
     * 首页分类
     *
     * @return
     */
    public void getCategory(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<HomeCategoryData> call = service.getCategory(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

//    /**
//     * 根据id获得产品信息
//     *
//     * @param listener
//     */
//    public void getProduct(int id, ResponseListener listener) {
//        HangXunCService service = getRetrofit().create(HangXunCService.class);
//        Call<ProductBean> call = service.getProduct(LoginUtils.getInstance().getScoId(), id,
//                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
//        call.enqueue(listener);
//    }

    /**
     * 搜索
     *
     * @param listener
     */
    public void searchPro(String keyWord, String sorts, String order, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<SearchResultData> call = service.searchPro(LoginUtils.getInstance().getScoId(),
                keyWord, sorts, order, LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }


    /**
     * 分类页数据接口
     *
     * @param listener
     */
    public void getCategoryPage(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<CategoryListData> call = service.getCategoryPage(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    /**
     * 品牌
     * @param listener
     */
    public void getManufacturer(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<CategoryListData> call = service.getManufacturer(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    /**
     * 语言切换
     *
     * @param listener
     */
    public void getLanguage(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<LanguageListData> call = service.getLanguage(LoginUtils.getInstance().getScoId());
        call.enqueue(listener);
    }

    /**
     * 货币切换
     */
    public void getCurrency(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<CurrencyListBean> call = service.getCurrency(LoginUtils.getInstance().getScoId());
        call.enqueue(listener);
    }


    /**
     * 语言切换
     *
     * @param listener
     */
    public void setLanguage(String language, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<LanguageListData> call = service.getLanguage(LoginUtils.getInstance().getScoId(), language);
        call.enqueue(listener);
    }

    /**
     * 货币切换
     */
    public void setCurrency(String currency, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<CurrencyListBean> call = service.getCurrency(LoginUtils.getInstance().getScoId(), currency);
        call.enqueue(listener);
    }


    /**
     * 支持国家
     */
    public void getCountry(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<CountryListData> call = service.getCountry(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    /**
     * 登录
     */
    public void login(@StreetApiConstants.LOGIN_TYPE String loginType, String callingCode,
                      String telephone, String email, String password, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<LoginData> call = service.login(loginType, callingCode, telephone, email, password, LoginUtils.getInstance().getSession());
        call.enqueue(listener);
    }

    /**
     * 注册
     */
    public void regist(@StreetApiConstants.LOGIN_TYPE String registType, String email, String callingCode,
                       String telephone, String smsCode, String password, String confirm,
                       String newsletter, String agree, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<RegistInfo> call = service.regist(1, registType, email, callingCode,
                telephone, smsCode, password, confirm, newsletter, agree);
        call.enqueue(listener);
    }

    public void smsCode(String calling_code, String phone, StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<SmsCodeData> call = service.smsCode(calling_code, phone);
        call.enqueue(listener);
    }

    public void isCustomerLogin(StreetResponseListener listener) {
        StreetHangXunCService service = getRetrofit().create(StreetHangXunCService.class);
        Call<IsLoginBean> call = service.isCustomerLogin(LoginUtils.getInstance().getScoId(), LoginUtils.getInstance().getSession());
        call.enqueue(listener);
    }

}
