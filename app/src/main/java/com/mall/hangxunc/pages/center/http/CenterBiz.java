package com.mall.hangxunc.pages.center.http;

import com.mall.hangxunc.bean.guide.IndustyModule;
import com.mall.hangxunc.http.HangXunCService;
import com.mall.hangxunc.http.LocalCookieJar;
import com.mall.hangxunc.http.ParamInterceptor;
import com.mall.hangxunc.http.ResponseListener;
import com.mall.hangxunc.pages.center.center.CenterHomeBean;
import com.mall.hangxunc.pages.center.center.CenterIsLoginBean;
import com.mall.hangxunc.utils.LanguageSp;
import com.mall.hangxunc.utils.LanguageUtil;
import com.mall.hangxunc.utils.LoginUtils;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CenterBiz {

    private static final String TAG = CenterBiz.class.getSimpleName();

    private Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new LocalCookieJar())
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(CenterApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }


    private Retrofit getPostRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ParamInterceptor())
                .cookieJar(new LocalCookieJar())
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(CenterApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    private CenterBiz() {
    }

    private static class Singleton {
        private static CenterBiz instance = new CenterBiz();
    }

    public static CenterBiz getInstance() {
        return Singleton.instance;
    }


    public void getIndex(ResponseListener listener) {
        CenterCService service = getRetrofit().create(CenterCService.class);
        Call<CenterHomeBean> call = service.getIndex(1, 4, LanguageSp.getInstance().getCode());
        call.enqueue(listener);
    }

    public void isCustomerLogin(ResponseListener listener) {
        CenterCService service = getRetrofit().create(CenterCService.class);
        Call<CenterIsLoginBean> call = service.isCustomerLogin(LoginUtils.getInstance().getScoId(), "app");
        call.enqueue(listener);
    }


    public void getAllIndusty(ResponseListener listener) {
        CenterCService service = getRetrofit().create(CenterCService.class);
        Call<IndustyModule> call = service.getAllIndusty();
        call.enqueue(listener);
    }

}
