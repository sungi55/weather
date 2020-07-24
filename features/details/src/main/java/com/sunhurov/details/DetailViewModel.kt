package com.sunhurov.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sunhurov.common.base.BaseViewModel
import com.sunhurov.common.utils.Event
import com.sunhurov.detail.R
import com.sunhurov.details.domain.GetCurrentConditionUseCase
import com.sunhurov.details.domain.GetDailyForecastUseCase
import com.sunhurov.details.domain.GetHourlyForecastUseCase
import com.sunhurov.model.CurrentCondition
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.HourlyForecast
import com.sunhurov.repository.AppDispatchers
import com.sunhurov.repository.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [DetailFragment].
 */
class DetailViewModel(
    private val getCurrentConditionUseCase: GetCurrentConditionUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val getDailyForecastUseCase: GetDailyForecastUseCase,
    private val dispatchers: AppDispatchers
): BaseViewModel() {

    // PRIVATE DATA
    private var argsLocationKey: String = ""
    private var currentConditionSource: LiveData<Resource<CurrentCondition>> = MutableLiveData()
    private var hourlyForecastSource: LiveData<Resource<List<HourlyForecast>>> = MutableLiveData()
    private var dailyForecastSource: LiveData<Resource<List<DailyForecast>>> = MutableLiveData()

    private val _currentCondition = MediatorLiveData<Resource<CurrentCondition>>()
    private val _hourlyForecast = MediatorLiveData<Resource<List<HourlyForecast>>>()
    private val _dailyForecast = MediatorLiveData<Resource<List<DailyForecast>>>()
    private val _isLoading = MutableLiveData<Resource.Status>()


    val currentCondition: LiveData<Resource<CurrentCondition>> get() = _currentCondition
    val hourlyForecast: LiveData<Resource<List<HourlyForecast>>> get() = _hourlyForecast
    val dailyForecast: LiveData<Resource<List<DailyForecast>>> get() = _dailyForecast
    val isLoading: LiveData<Resource.Status> get() = _isLoading


    // PUBLIC ACTIONS ---
    fun loadDetailsWhenActivityStarts(locationKey: String) {
        argsLocationKey = locationKey
        getCurrentConditionDetail(locationKey, false)
        getHourlyForecastDetail(argsLocationKey, false)
        getDailyForecastDetail(argsLocationKey, false)
    }


    fun reloadDataWhenForecastRefreshes() {
        getCurrentConditionDetail(argsLocationKey, true)
        getHourlyForecastDetail(argsLocationKey, true)
        getDailyForecastDetail(argsLocationKey, true)
    }


    // ---

    private fun getCurrentConditionDetail(key: String, forceRefresh: Boolean): Job {
        return viewModelScope.launch(dispatchers.main) {
            // We make sure there is only one source of livedata (allowing us properly refresh)
            _currentCondition.removeSource(currentConditionSource)

            withContext(dispatchers.io) {
                currentConditionSource =
                    getCurrentConditionUseCase(forceRefresh = forceRefresh, key = key)
            }

            _currentCondition.addSource(currentConditionSource) {
                _currentCondition.value = it
                if (it.status == Resource.Status.ERROR) _snackbarError.value =
                    Event(R.string.an_error_happened)
            }
        }
    }

    private fun getHourlyForecastDetail(key: String, forceRefresh: Boolean): Job {
        return viewModelScope.launch(dispatchers.main) {
            // We make sure there is only one source of livedata (allowing us properly refresh)
            _hourlyForecast.removeSource(hourlyForecastSource)

            withContext(dispatchers.io) {
                hourlyForecastSource =
                    getHourlyForecastUseCase(forceRefresh = forceRefresh, key = key)
            }

            _hourlyForecast.addSource(hourlyForecastSource) {
                _hourlyForecast.value = it
                if (it.status == Resource.Status.ERROR) _snackbarError.value =
                    Event(R.string.an_error_happened)
            }
        }
    }


    private fun getDailyForecastDetail(key: String, forceRefresh: Boolean): Job {
        return viewModelScope.launch(dispatchers.main) {
            // We make sure there is only one source of livedata (allowing us properly refresh)
            _dailyForecast.removeSource(dailyForecastSource)

            withContext(dispatchers.io) {
                dailyForecastSource =
                    getDailyForecastUseCase(forceRefresh = forceRefresh, key = key)
            }

            _dailyForecast.addSource(dailyForecastSource) {
                _dailyForecast.value = it
                if (it.status == Resource.Status.ERROR) _snackbarError.value =
                    Event(R.string.an_error_happened)
            }
        }

    }
}