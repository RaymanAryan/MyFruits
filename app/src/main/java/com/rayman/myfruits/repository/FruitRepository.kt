package com.rayman.myfruits.repository

import android.util.Log
import com.rayman.myfruits.data.model.ApiResponse
import com.rayman.myfruits.data.model.Attribute
import com.rayman.myfruits.data.model.FruitviceResponse
import com.rayman.myfruits.data.model.Nutrients
import com.rayman.myfruits.data.network.FruitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FruitRepository @Inject constructor(
    private val fruitService: FruitService
) {

    fun getAllFruits(): Flow<ApiResponse> = flow {
        try {
            emit(FruitviceResponse(fruitService.getAllFruits()))
            Log.d("FruitRepository", fruitService.getAllFruits().toString())
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }

    fun getFruitByAttribute(attribute: Attribute,attributeValue: String): Flow<ApiResponse> = flow {
        try {
            when (attribute) {
                Attribute.Genus -> emit(FruitviceResponse(fruitService.getFruitByGenus(attributeValue)))
                Attribute.Family -> emit(FruitviceResponse(fruitService.getFruitByFamily(attributeValue)))
                Attribute.Order -> emit(FruitviceResponse(fruitService.getFruitByOrder(attributeValue)))
                else -> getAllFruits()
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }

    fun getFruitByNutrient(nutrientName: Nutrients, min: Double?, max: Double?): Flow<ApiResponse> = flow {
        try {
            emit(FruitviceResponse(fruitService.getFruitByNutrition(nutrientName.name.lowercase(), min, max)))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }
}
