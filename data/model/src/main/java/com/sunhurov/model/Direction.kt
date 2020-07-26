package com.sunhurov.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Direction(
    @SerializedName("Degrees")
    var degrees: Int? = null,
    @SerializedName("English")
    var english: String? = null,
    @SerializedName("Localized")
    var localized: String? = null
)