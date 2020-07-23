package com.sunhurov.remote

import com.sunhurov.model.ApiResult
import com.sunhurov.model.Weather
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {


    @GET("weather/{id}?")
    fun fetchCountryDetailsAsync(@Path("id") id: Int,
                               @Query("api_key") key: String): Deferred<Weather>

    @GET("search/country?")
    fun fetchDetailsByKeywordAsync(@Query("query") keyword: String,
                                 @Query("api_key") key: String): Deferred<ApiResult<Weather>>
}