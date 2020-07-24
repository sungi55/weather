package com.sunhurov.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Country(

    @SerializedName("ID")
    @ColumnInfo(name = "country_code")
    var iD: String? = null,

    @ColumnInfo(name = "country_localized_name")
    @SerializedName("LocalizedName")
    var localizedName: String? = null
)