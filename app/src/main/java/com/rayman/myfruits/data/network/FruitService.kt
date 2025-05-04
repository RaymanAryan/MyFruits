package com.rayman.myfruits.data.network


import com.rayman.myfruits.data.model.Fruit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FruitService{
    @GET("/api/fruit/all")
    suspend fun getAllFruits(): List<Fruit>

    @GET("/api/fruit/genus/{attributeValue}")
    suspend fun getFruitByGenus(
        @Path("attributeValue") attributeValue: String
    ): List<Fruit>

    @GET("/api/fruit/family/{attributeValue}")
    suspend fun getFruitByFamily(
        @Path("attributeValue") attributeValue: String
    ): List<Fruit>

    @GET("/api/fruit/order/{attributeValue}")
    suspend fun getFruitByOrder(
        @Path("attributeValue") attributeValue: String
    ): List<Fruit>

    @GET("/api/fruit/{nutrition}")
    suspend fun getFruitByNutrition(
        @Path("nutrition") nutrition: String,
        @Query("min") min: Double? = null, // Optional
        @Query("max") max: Double? = null  // Optional
    ): List<Fruit>
}