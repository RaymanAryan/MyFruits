package com.rayman.myfruits.data.model

import androidx.annotation.Keep

@Keep data class FruitviceResponse(val results: List<Fruit>): ApiResponse()