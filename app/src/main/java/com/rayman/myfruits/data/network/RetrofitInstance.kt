package com.rayman.myfruits.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object RetrofitInstance {
    private const val BASE_URL = "https://www.fruityvice.com/"

    val fruitService: FruitService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FruitService::class.java)
    }
}
