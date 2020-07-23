package com.sunhurov.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Yurii Sunhurov on 13.05.2020
 */

data class ApiResult<T>(
    @SerializedName("total_pages") val totalCount: Int,
    @SerializedName("results") val items: List<T>
)