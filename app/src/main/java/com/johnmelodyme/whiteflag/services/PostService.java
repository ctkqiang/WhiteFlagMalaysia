package com.johnmelodyme.whiteflag.services;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface PostService
{
    @FormUrlEncoded
    @POST("/flag/api/data/")
    void insertUser(
            @Field("name") String userName,
            @Field("phone") String phoneNumber,
            @Field("home") String homeAddress,
            @Field("desp") String description,
            Callback<Response> callback
    );
}
