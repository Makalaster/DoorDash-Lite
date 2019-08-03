package com.makalaster.doordashlite

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    private const val BASE_URL = "https://api.doordash.com"
    val getClient: RestaurantApi
        get() {
            val gson = GsonBuilder().create()
            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(RestaurantApi::class.java)
        }
}

interface RestaurantApi {
    @GET("/v2/restaurant/?lat=37.422740&lng=-122.139956&offset=0&limit=50")
    fun getRestaurants(): Call<List<Restaurant>>
}