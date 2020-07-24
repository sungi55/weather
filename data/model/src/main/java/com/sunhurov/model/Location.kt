package com.sunhurov.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Location(

    @SerializedName("Key")
    @PrimaryKey
    var key: String,

    @SerializedName("AdministrativeArea")
    @Embedded
    var administrativeArea: AdministrativeArea? = null,

    @SerializedName("Country")
    @Embedded
    var country: Country? = null,

    @SerializedName("LocalizedName")
    @ColumnInfo(name = "localized_name")
    var localizedName: String? = null,

    @SerializedName("Rank")
    @ColumnInfo(name = "rank")
    var rank: Int? = null,

    @SerializedName("Type")
    @ColumnInfo(name = "type")
    var type: String? = null,

    @SerializedName("Version")
    @ColumnInfo(name = "version")
    var version: Int? = null
)