package com.example.uniporter_app.API;

import com.example.uniporter_app.API_models.DefaultResponse;
import com.example.uniporter_app.API_models.LoginResponse;
import com.example.uniporter_app.API_models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {


    @FormUrlEncoded
    @POST("create/")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("token/")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("me/")
    Call<UserResponse> getUser(
            @Header("Authorization") String authHeader
    );
}