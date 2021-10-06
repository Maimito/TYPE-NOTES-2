package com.maimito.type_notes.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DBConnect {

    public static DBService getDB(){
        String ipaddress = "http://192.168.137.1/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipaddress + "typenotes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DBService service = retrofit.create(DBService.class);
        return service;
    }
}
