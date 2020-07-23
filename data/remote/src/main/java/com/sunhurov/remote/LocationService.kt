package com.sunhurov.remote

import com.sunhurov.model.ApiResult
import com.sunhurov.model.Location
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationService {


    @GET("/locations/v1/cities/autocomplete?")
    fun fetchMovieByKeywordAsync(
        @Query("apikey") apiKey: String,
        @Query("q") keyword: String
    ): Deferred<ApiResult<Location>>
}