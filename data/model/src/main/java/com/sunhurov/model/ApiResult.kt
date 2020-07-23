package com.sunhurov.model

import com.google.gson.annotations.SerializedName

data class ApiResult<T>(
    @SerializedName("total_pages") val totalCount: Int,
    @SerializedName("results") val items: List<T>
)