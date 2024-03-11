package com.mita.gamebuddymobile.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("login")
    fun login(@Body login: Login): Call<LoginResponse>

    @POST("register")
    fun register(@Body user: User): Call<User>

    @GET("/user/profile")
    fun getProfile(@Header("Authorization") token: String): Call<ProfileResponse>

    @GET("list")
    fun getUser(): Call<List<UserDataClass>>
}
