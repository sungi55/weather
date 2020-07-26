package com.sunhurov.model


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Pressure(

    @Embedded(prefix = "pressure_imperial_")
    @SerializedName("Imperial")
    var imperial: Imperial? = null,

    @Embedded(prefix = "pressure_metric_")
    @SerializedName("Metric")
    var metric: Metric? = null
)