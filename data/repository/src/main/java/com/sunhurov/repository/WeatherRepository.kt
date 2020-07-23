package com.sunhurov.repository

import com.sunhurov.local.dao.WeatherDao
import com.sunhurov.remote.WeatherDatasource

interface WeatherRepository {
}

class WeatherRepositoryImpl(
    private val datasource: WeatherDatasource,
    private val dao: WeatherDao
): WeatherRepository {


}