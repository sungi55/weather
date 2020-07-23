package com.sunhurov.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Yurii Sunhurov on 13.05.2020
 */

@Entity
data class Weather (

    @PrimaryKey
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    var lastRefreshed: Date
    
) {
    /**
     * We consider that an [Weather] is outdated when the last time
     * we fetched it was more than 10 minutes
     */
    fun haveToRefreshFromNetwork() : Boolean
            = TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= 10


}