package com.sunhurov.remote

class WeatherDatasource(private val weatherService: WeatherService) {

    fun fetchLocationsAsync(apiKey:String, keyword:String)
            = weatherService.fetchLocationsByKeywordAsync( apiKey = apiKey, keyword = keyword)

    fun fetchCurrentCondition(apiKey: String, locationKey:String)
            = weatherService.fetchCurrentConditionByLocationKeyAsync(apiKey = apiKey, locationKey = locationKey)

    fun fetchHourlyForecast(apiKey: String, locationKey: String)
            = weatherService.fetch12HoursForecastByLocationKeyAsync(apiKey = apiKey, locationKey = locationKey)

    fun fetchDailyForecast(apiKey: String, locationKey: String)
            = weatherService.fetch5DaysForecastByLocationKeyAsync(apiKey = apiKey, locationKey = locationKey)

}