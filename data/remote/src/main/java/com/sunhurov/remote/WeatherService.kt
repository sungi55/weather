package com.sunhurov.remote

import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.HourlyForecast
import com.sunhurov.model.Location
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("/locations/v1/cities/autocomplete")
    fun fetchLocationsByKeywordAsync(
        @Query("q") keyword: String,
        @Query("apikey") apiKey: String
    ): Deferred<List<Location>>

    @GET("/currentconditions/v1/{location_key}")
    fun fetchCurrentConditionByLocationKeyAsync(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String
    ): Deferred<CurrentCondition>


    @GET("/forecasts/v1/hourly/12hour/{location_key}")
    fun fetch12HoursForecastByLocationKeyAsync(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String,
        @Query("metric") metric: Boolean = true
    ): Deferred<List<HourlyForecast>>

    @GET(" /forecasts/v1/daily/5day/{location_key}")
    fun fetch5DaysForecastByLocationKeyAsync(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String,
        @Query("metric") metric: Boolean = true
    ): Deferred<List<DailyForecast>>

}