package com.sunhurov.model


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Speed(
    @Embedded(prefix = "speed_imperial_")
    @SerializedName("Imperial")
    var imperial: Imperial? = null,

    @Embedded(prefix = "speed_metric_")
    @SerializedName("Metric")
    var metric: Metric? = null
)