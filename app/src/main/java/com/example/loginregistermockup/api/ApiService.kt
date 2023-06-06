package com.example.loginregistermockup.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun loginApi(@Field("username") username: String, @Field("password") password: String) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun registerApi(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String,
        @Field("nama") fullname: String,
        @Field("namaSekolah") namaSekolah: String? = null,
        @Field("email") email: String
    ) : Call<RegisterResponse>
}