package com.app.agoralivestream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Webservices {
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    private Retrofit.Builder retrofit;
    private WebApi webApi;
    private static Webservices mInstance;

    public Webservices() {
        mInstance = this;
        gson=new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

         okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .callTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        retrofit = new Retrofit.Builder();
    }

    public static Webservices getInstance() {
        return mInstance;
    }
    public WebApi getWebApi(){
        webApi= retrofit.baseUrl("https://asija.000webhostapp.com/agora/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(WebApi.class);
        return webApi;
    }

}

interface WebApi{
    @GET("RtcSample.php")
    public Call<Response> getToken(
            @Query("role") int role,
            @Query("channel_name") String channelName);
}