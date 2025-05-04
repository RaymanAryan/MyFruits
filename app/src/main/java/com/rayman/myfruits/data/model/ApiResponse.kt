package com.rayman.myfruits.data.model

import androidx.annotation.Keep

@Keep sealed class ApiResponse{
    data class Error(val exception: Throwable) : ApiResponse()
    object Loading : ApiResponse()
}
