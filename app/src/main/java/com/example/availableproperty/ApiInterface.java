package com.example.availableproperty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("detail")
    Call<List<PropertyModel>> getProperty();
}
