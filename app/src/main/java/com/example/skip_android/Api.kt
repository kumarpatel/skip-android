package com.example.skip_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/restaurants/bypostcode/{outcode}")
    fun fetchRestaurants(@Path("outcode") outcode: String): Call<RestaurantResponse>


}