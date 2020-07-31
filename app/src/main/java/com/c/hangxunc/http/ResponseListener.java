package com.c.hangxunc.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseListener<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            onSuccess(response.body());
        } else {
            onFail(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(-1, t.getMessage());
    }

    public abstract void onFail(int code, String message);

    public abstract void onSuccess(T t);

}
