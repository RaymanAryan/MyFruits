package com.rayman.myfruits.data.model

import androidx.annotation.Keep

@Keep data class Nutrition(
    val calories: Double?,
    val fat: Double?,
    val sugar: Double?,
    val carbohydrates: Double?,
    val protein: Double?
)

@Keep enum class Nutrients {
    Empty,
    Calories,
    Fat,
    Sugar,
    Carbohydrates,
    Protein
}