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
import com.c.hangxunc.utils.CurrencySp;
import com.c.hangxunc.utils.LanguageSp;
import com.c.hangxunc.utils.LoginUtils;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HangXunBiz {

    private static final String TAG = HangXunBiz.class.getSimpleName();

    private Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new LocalCookieJar())
//                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
//                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }


    private Retrofit getPostRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ParamInterceptor())
//                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
//                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .cookieJar(new LocalCookieJar())
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }


    private HangXunBiz() {
    }

    private static class Singleton {
        private static HangXunBiz instance = new HangXunBiz();
    }

    public static HangXunBiz getInstance() {
        return Singleton.instance;
    }


    public void getHomeTop(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
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
    public void getAllProduct(int count, ResponseListener listener) {
        HangXunCService service = getPostRetrofit().create(HangXunCService.class);
        Call<ProductListBean> call = service.getAllProduct(LoginUtils.getInstance().getScoId(), count, ApiConstants.LIMIT,
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    public void addShopCart(int product_id, int quantity, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
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
    public void getCategory(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
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
    public void searchPro(String keyWord, String sorts, String order, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<SearchResultData> call = service.searchPro(LoginUtils.getInstance().getScoId(),
                keyWord, sorts, order, LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }


    /**
     * 分类页数据接口
     *
     * @param listener
     */
    public void getCategoryPage(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<CategoryListData> call = service.getCategoryPage(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    /**
     * 品牌
     * @param listener
     */
    public void getManufacturer(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<CategoryListData> call = service.getManufacturer(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    /**
     * 语言切换
     *
     * @param listener
     */
    public void getLanguage(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<LanguageListData> call = service.getLanguage(LoginUtils.getInstance().getScoId());
        call.enqueue(listener);
    }

    /**
     * 货币切换
     */
    public void getCurrency(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<CurrencyListData> call = service.getCurrency(LoginUtils.getInstance().getScoId());
        call.enqueue(listener);
    }


    /**
     * 语言切换
     *
     * @param listener
     */
    public void setLanguage(String language, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<LanguageListData> call = service.getLanguage(LoginUtils.getInstance().getScoId(), language);
        call.enqueue(listener);
    }

    /**
     * 货币切换
     */
    public void setCurrency(String currency, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<CurrencyListData> call = service.getCurrency(LoginUtils.getInstance().getScoId(), currency);
        call.enqueue(listener);
    }


    /**
     * 支持国家
     */
    public void getCountry(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<CountryListData> call = service.getCountry(LoginUtils.getInstance().getScoId(),
                LanguageSp.getInstance().getCode(), CurrencySp.getInstance().getCode());
        call.enqueue(listener);
    }

    /**
     * 登录
     */
    public void login(@ApiConstants.LOGIN_TYPE String loginType, String callingCode,
                      String telephone, String email, String password, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<LoginData> call = service.login(loginType, callingCode, telephone, email, password, LoginUtils.getInstance().getSession());
        call.enqueue(listener);
    }

    /**
     * 注册
     */
    public void regist(@ApiConstants.LOGIN_TYPE String registType, String email, String callingCode,
                       String telephone, String smsCode, String password, String confirm,
                       String newsletter, String agree, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<RegistInfo> call = service.regist(1, registType, email, callingCode,
                telephone, smsCode, password, confirm, newsletter, agree);
        call.enqueue(listener);
    }

    public void smsCode(String calling_code, String phone, ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<SmsCodeData> call = service.smsCode(calling_code, phone);
        call.enqueue(listener);
    }

    public void isCustomerLogin(ResponseListener listener) {
        HangXunCService service = getRetrofit().create(HangXunCService.class);
        Call<IsLoginBean> call = service.isCustomerLogin(LoginUtils.getInstance().getScoId(), LoginUtils.getInstance().getSession());
        call.enqueue(listener);
    }

}
