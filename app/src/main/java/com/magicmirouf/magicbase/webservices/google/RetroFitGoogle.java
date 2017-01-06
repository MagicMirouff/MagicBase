package com.magicmirouf.magicbase.webservices.google;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magicmirouf.magicbase.webservices.google.models.NearbySearch;
import com.magicmirouf.magicbase.webservices.google.models.PlaceDetails;
import com.magicmirouf.magicbase.webservices.google.models.Predictions;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sylva on 28/07/2016.
 */
public class RetroFitGoogle {

    public static final String TAG = "RetroFit call : ";
    private static Service service;
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    private static final String key = "AIzaSyAu4PYWRAVBeRt738CvqnjD3LWXUQC6gjU";
    //private static final String key = "AIzaSyDWuJMSCNnZKmEnPoegyRICQAkJ7833wJI";

    public static void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();//.addInterceptor(interceptor);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json");

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

    public static Service getService() {
        return service;
    }

    public interface Service {

        @GET("autocomplete/json?components=country:fr&key=" + key)
        Call<Predictions> autocomplete(@Query("input") String input);

        @GET("nearbysearch/json?rankby=distance&keyword=restaurant&key=" + key)
        Call<NearbySearch> nearbySearch(@Query("location") String location);

        @GET("radarsearch/json?rankby=distance&types=establishment&key=" + key)
        Call<NearbySearch> radarSearch(@Query("keyword") String keyword, @Query("location") String location, @Query("radius") int radius);

        @GET("details/json?key=" + key)
        Call<PlaceDetails> getPlaceDetails(@Query("placeid") String placeId);

        @GET("photo?key=" + key)
        Call<Object> getPhoto(@Query("photoreference") String reference, @Query("maxwidth") int maxwidth);


    }




}
