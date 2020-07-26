package com.sunhurov.model


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Temperature(
    @Embedded(prefix = "imperial_")
    @SerializedName("Imperial")
    var imperial: Imperial? = null,

    @Embedded(prefix = "metric_")
    @SerializedName("Metric")
    var metric: Metric? = null
)