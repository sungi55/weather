package com.sunhurov.details

import androidx.lifecycle.*
import com.sunhurov.common.base.BaseViewModel
import com.sunhurov.common.utils.Event
import com.sunhurov.detail.R
import com.sunhurov.details.domain.GetCurrentConditionUseCase
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
    private val dispatchers: AppDispatchers
): BaseViewModel() {

    // PRIVATE DATA
    private var argsLocationKey: String = ""
    private var currentConditionSource: LiveData<Resource<CurrentCondition>> = MutableLiveData()
    private var hourlyForecastSource: LiveData<Resource<List<HourlyForecast>>> = MutableLiveData()

    private val _currentCondition = MediatorLiveData<CurrentCondition>()
    private val _hourlyForecast = MediatorLiveData<Resource<List<HourlyForecast>>>()


    val currentCondition: LiveData<CurrentCondition> get() = _currentCondition
    val hourlyForecast: LiveData<Resource<List<HourlyForecast>>> get() = _hourlyForecast

    //return temperature font color
    val temperatureColorRes:Int get() {
        return currentCondition.value?.temperature?.metric?.value?.let {
             when {
                it <= -10.0 -> R.color.colorBlue600
                it in -10.0..20.0 -> R.color.colorPrimaryText
                it >= 20.0 -> R.color.colorRed600
                else -> R.color.colorPrimaryText
            }
        }?:R.color.colorPrimaryText
    }

    // PUBLIC ACTIONS ---
    fun loadDetailsWhenActivityStarts(locationKey: String) {
        argsLocationKey = locationKey
        getCurrentConditionDetail(argsLocationKey, false)
        getHourlyForecastDetail(argsLocationKey, false)
    }


    fun reloadDataWhenForecastRefreshes() {
        getCurrentConditionDetail(argsLocationKey, true)
        getHourlyForecastDetail(argsLocationKey, true)
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
                    _currentCondition.value = it.data
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

}