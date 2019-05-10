package com.example.soccerstats.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static final String BASE_URL = "http://soccer.sportsopendata.net/";
    public static final String TAG = RetrofitHelper.class.getSimpleName();
    public static SoccerApi soccerApi;

    public static void initializeRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        soccerApi = retrofit.create(SoccerApi.class);
    }


}
