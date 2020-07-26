package com.sunhurov.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

@Entity
data class HourlyForecast(

    @PrimaryKey(autoGenerate = true)
    val id:Int,

    var key:String,

    @SerializedName("DateTime")
    var dateTime: String? = null,

    @SerializedName("EpochDateTime")
    var epochDateTime: Int? = null,

    @SerializedName("IconPhrase")
    var iconPhrase: String? = null,

    @SerializedName("IsDaylight")
    var isDaylight: Boolean? = null,

    @Embedded(prefix = "hourly_temp_")
    @SerializedName("Temperature")
    var temperature: Metric? = null,

    @SerializedName("WeatherIcon")
    var weatherIcon: Int? = null,

    var lastRefreshed: Date

) {
    /**
     * We consider that an [HourlyForecast] is outdated when the last time
     * we fetched it was more than 60 minutes
     */
    fun haveToRefreshFromNetwork() : Boolean
            = TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 60


}