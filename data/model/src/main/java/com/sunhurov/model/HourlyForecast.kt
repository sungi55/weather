package com.sunhurov.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Yurii Sunhurov on 24.07.2020
 */

@Entity
data class HourlyForecast(

    @PrimaryKey
    val id:Int,

    val min:Int,

    val max:Int,

    val icon:Int,

    val metric: String,

    val time:Long,

    var lastRefreshed: Date

) {
    /**
     * We consider that an [HourlyForecast] is outdated when the last time
     * we fetched it was more than 60 minutes
     */
    fun haveToRefreshFromNetwork() : Boolean
            = TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 60


}