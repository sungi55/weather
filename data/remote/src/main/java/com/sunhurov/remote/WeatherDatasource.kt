package com.sunhurov.remote

class WeatherDatasource(private val weatherService: WeatherService) {

    fun fetchLocationsAsync(apiKey:String, keyword:String)
            = weatherService.fetchLocationsByKeywordAsync( apiKey = apiKey, keyword = keyword)

    fun fetchCurrentConditionAsync(apiKey: String, locationKey:String)
            = weatherService.fetchCurrentConditionByLocationKeyAsync(apiKey = apiKey, locationKey = locationKey)

    fun fetchHourlyForecastAsync(apiKey: String, locationKey: String)
            = weatherService.fetch12HoursForecastByLocationKeyAsync(apiKey = apiKey, locationKey = locationKey)


}