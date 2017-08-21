package com.example.root.google.Api;

import com.example.root.google.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    /*
    * Center API
    * */
    //public static final String BASE_URL = "http://cariba.esy.es/web/api.php/v1/";
    public static final String BASE_URL = "http://cariba.datakreatif.com/web/api.php/v1/";
    public static final Integer READ_TIMEOUT = 60;
    public static final Integer CONNECT_TIMEOUT = 60;
    /*
    * Initiate retrofit
    * */
    private static Retrofit retrofit = null;
    /*
    * Set Connection time out
    * Set Read time out
    * */
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build();
    /*
    * Get/Return retrofit object
    * */



    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}