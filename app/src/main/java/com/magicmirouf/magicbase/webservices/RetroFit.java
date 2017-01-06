package com.magicmirouf.magicbase.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magicmirouf.magicbase.utils.config.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sylva on 28/07/2016.
 */
public class RetroFit {

    private static Service service;
    public static String token;
    public static String BASE_URL = "http://www.koockin.com/www/apievent/v1/";

    public static void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(interceptor);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json");

                if (token !=null){
                    request.addHeader("AuthKey",token);
                }
                return chain.proceed(request.build());
            }
        });

        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(Service.class);
    }

    public static void setToken(String token) {
        RetroFit.token = token;
        init();
        Config.Log("WS token",token);
    }


    public interface Service {

    }

    public static Service getService() {
        return service;
    }


    // Constantes
}
