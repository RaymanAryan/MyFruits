package com.rayman.myfruits.data.model

import androidx.annotation.Keep

@Keep data class Fruit(val name: String?,
                 val genus: String?,
                 val family: String?,
                 val order: String?,
                 val nutritions : Nutrition)
