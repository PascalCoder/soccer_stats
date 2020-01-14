package com.example.soccerstats.model;

import com.example.soccerstats.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static SoccerApi soccerApi;

    public static void initializeRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient().build())
                .build();

        soccerApi = retrofit.create(SoccerApi.class);
    }

    private static OkHttpClient.Builder getOkHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .cache(null);
    }
}
