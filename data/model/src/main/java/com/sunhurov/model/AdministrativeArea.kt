package com.sunhurov.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated


@Entity
data class AdministrativeArea(

    @SerializedName("ID")
    @ColumnInfo(name = "area_code")
    var iD: String? = null,

    @SerializedName("LocalizedName")
    @ColumnInfo(name = "area_localized_name")
    var localizedName: String? = null
)