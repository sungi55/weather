package com.sunhurov.remote

class LocationDatasource(private val locationService: LocationService) {

    fun fetchLocationsAsync(apiKey:String, keyword:String) =
        locationService.fetchMovieByKeywordAsync(keyword = keyword, apiKey = apiKey)

}