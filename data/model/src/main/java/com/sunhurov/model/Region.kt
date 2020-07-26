package com.sunhurov.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Region(

    @SerializedName("ID")
    var id: String? = null,

    @SerializedName("LocalizedName")
    var localizedName: String? = null
)