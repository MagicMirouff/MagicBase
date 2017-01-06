package com.magicmirouf.magicbase.webservices;

import android.content.Context;

import com.magicmirouf.magicbase.navigation.BaseActivity;
import com.magicmirouf.magicbase.utils.config.Config;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sylva on 07/09/2016.
 */
public class MyCallback<T> implements Callback<T> {

    private Context context;

    public MyCallback() {

    }
    public MyCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // call
        Config.LogE("Call",call.request().toString());
        if (call.request().body() instanceof FormBody) {
            FormBody body = ((FormBody) (call.request().body()));
            for (int i = 0; i < body.size(); i++) {
                Config.LogE(body.encodedName(i), body.encodedValue(i));
            }
        }
        // answer
        if (response.body() !=null){
            Config.LogE("Response",response.body().toString());
        } else if (response.errorBody() !=null){
            Config.LogE("ErrorResponse",response.errorBody().toString());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Config.Log("Failure",t.getMessage());
        // progress
    }
}
