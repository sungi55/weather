package com.sunhurov.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Yurii Sunhurov on 24.07.2020
 */

@Entity
data class DailyForecast(

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
     * We consider that an [DailyForecast] is outdated when the last time
     * we fetched it was more than 1 day
     */
    fun haveToRefreshFromNetwork() : Boolean
            = TimeUnit.MILLISECONDS.toDays(Date().time - lastRefreshed.time) >= 1


}