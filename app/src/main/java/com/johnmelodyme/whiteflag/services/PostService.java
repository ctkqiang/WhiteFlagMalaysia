package com.johnmelodyme.whiteflag.services;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
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

    @FormUrlEncoded
    @POST("/flag/api/status/")
    void helpStatus(
            @Field("phone") String phone,
            @Field("name") String name,
            Callback<Response> callback
    );

    @FormUrlEncoded
    @GET("/flag/api/search/")
    void searchData(
            @Field("keyword") String keyword,
            Callback<Response> callback
    );

    @FormUrlEncoded
    @GET("/flag/api/list/")
    void searchByState(
            @Field("state") String state,
            Callback<Response> callback
    );
}
