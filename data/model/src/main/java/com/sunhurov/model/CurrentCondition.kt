package com.sunhurov.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

@Entity
data class CurrentCondition(

    @PrimaryKey
    var key:String,

    @SerializedName("EpochTime")
    var epochTime: Int? = null,

    @SerializedName("LocalObservationDateTime")
    var localObservationDateTime: String? = null,

    @Embedded
    @SerializedName("Pressure")
    var pressure: Pressure? = null,

    @SerializedName("RelativeHumidity")
    var relativeHumidity: Int? = null,

    @Embedded(prefix = "current_temp_")
    @SerializedName("Temperature")
    var temperature: Temperature? = null,

    @SerializedName("WeatherIcon")
    var weatherIcon: Int? = null,

    @SerializedName("WeatherText")
    var weatherText: String? = null,

    @Embedded
    @SerializedName("Wind")
    var wind: Wind? = null,

    var lastRefreshed: Date
) {
    /**
     * We consider that an [CurrentCondition] is outdated when the last time
     * we fetched it was more than 10 min
     */
    fun haveToRefreshFromNetwork() : Boolean
            = TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 10


}