package com.mall.hangxunc.pages.center.http;


import com.mall.hangxunc.pages.center.center.CenterHomeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CenterCService {


    @GET(CenterApiConstants.HOME_DATA)
    Call<CenterHomeBean> getIndex(@Query("page") int count, @Query("limit") int limit, @Query("languageCode") String language);

}