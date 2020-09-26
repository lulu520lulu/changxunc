package com.mall.hangxunc.pages.center.http;


import com.mall.hangxunc.bean.guide.IndustyModule;
import com.mall.hangxunc.pages.center.center.CenterHomeBean;
import com.mall.hangxunc.pages.center.center.CenterIsLoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CenterCService {

    @GET(CenterApiConstants.GET_ALL_INDUSTY)
    Call<IndustyModule> getAllIndusty();

    @GET(CenterApiConstants.HOME_DATA)
    Call<CenterHomeBean> getIndex(@Query("page") int count, @Query("limit") int limit, @Query("languageCode") String language);

    /**
     * 检查登录状态
     */
    @GET(CenterApiConstants.IS_CUSTOMER_LOGIN)
    Call<CenterIsLoginBean> isCustomerLogin(@Query("sco") String sco, @Query("app") String app);
}