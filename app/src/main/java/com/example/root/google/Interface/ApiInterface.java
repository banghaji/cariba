package com.example.root.google.Interface;

import com.example.root.google.model.JsonRequest;
import com.example.root.google.model.Product;
import com.example.root.google.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by root on 21/06/17.
 */

public interface ApiInterface {

    @POST("search")
    Call<List<Product>> getAscProduct(@Body JsonRequest m);
}
