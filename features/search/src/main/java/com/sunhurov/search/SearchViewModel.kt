package com.sunhurov.search

import android.text.TextUtils
import androidx.lifecycle.*
import com.sunhurov.common.base.BaseViewModel
import com.sunhurov.common.utils.Event
import com.sunhurov.model.Location
import com.sunhurov.repository.AppDispatchers
import com.sunhurov.repository.utils.Resource
import com.sunhurov.search.domain.SaveLocationUseCase
import com.sunhurov.search.domain.SearchLocationsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [SearchFragment].
 */
class SearchViewModel(
    private val getSearchLocationUseCase: SearchLocationsUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    // FOR DATA

    var searchText: String = ""
        set(value) {
            field = value
            searchLocation()
        }

    private val _locations = MediatorLiveData<Resource<List<Location>>>()
    val locations: LiveData<Resource<List<Location>>> get() = _locations
    private var locationSource: LiveData<Resource<List<Location>>> = MutableLiveData()


    // PUBLIC ACTIONS ---
    fun locationClicksOnItem(location: Location) {
        saveLocation(location)
    }


    fun locationsRefreshesItems() {
        searchLocation()
    }

    // ---

    private fun searchLocation(){
        if(TextUtils.isEmpty(searchText)) {
            resetSearch()
            return
        }
        searchLocations(searchText)
    }

    private fun searchLocations(query: String) = viewModelScope.launch(dispatchers.main) {
        _locations.removeSource(locationSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        withContext(dispatchers.io) { locationSource = getSearchLocationUseCase(keyword = query) }
        _locations.addSource(locationSource) {
            _locations.value = it
            if (it.status == Resource.Status.ERROR) _snackbarError.value =
                Event(R.string.an_error_happened)
        }
    }

    private fun resetSearch() = viewModelScope.launch(dispatchers.main) {
        _locations.removeSource(locationSource) // We make sure there is only one source of livedata (allowing us properly refresh)
        _locations.addSource(locationSource) {
            _locations.value = Resource.success(emptyList())
        }

    }

    private fun saveLocation(location: Location)  = viewModelScope.launch(dispatchers.main) {
        withContext(dispatchers.io) {
            saveLocationUseCase(location)
        }
//        openDetailFragment(location)
    }

    private fun openDetailFragment(location: Location) {
        location.key?.let {
            navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(it))
        }
    }

}