package com.sunhurov.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Metric(
    @SerializedName("Unit")
    var unit: String? = null,
    @SerializedName("UnitType")
    var unitType: Int? = null,
    @SerializedName("Value")
    var value: Double? = null
)