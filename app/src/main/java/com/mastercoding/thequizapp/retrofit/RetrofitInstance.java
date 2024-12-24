package com.mastercoding.thequizapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    //to use in emulator we cannot use this 127.0.0.1
    String baseUrl = "http://10.0.2.2/quiz/";

    //Create and return a configured Retrofit instance
    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
