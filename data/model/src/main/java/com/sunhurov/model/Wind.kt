package com.sunhurov.model


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Wind(
    @Embedded
    @SerializedName("Direction")
    var direction: Direction? = null,

    @Embedded
    @SerializedName("Speed")
    var speed: Speed? = null
)