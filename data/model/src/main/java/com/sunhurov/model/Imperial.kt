package com.sunhurov.model


import com.google.gson.annotations.SerializedName


data class Imperial(
    @SerializedName("Unit")
    var unit: String? = null,
    @SerializedName("UnitType")
    var unitType: Int? = null,
    @SerializedName("Value")
    var value: Double? = null
)