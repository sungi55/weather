package com.sunhurov.home

import androidx.lifecycle.*
import com.sunhurov.common.base.BaseViewModel
import com.sunhurov.common.utils.Event
import com.sunhurov.home.domain.GetLocationsUseCase
import com.sunhurov.model.Location
import com.sunhurov.repository.AppDispatchers
import com.sunhurov.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [HomeFragment].
 */
class HomeViewModel(private val getLocationsUseCase: GetLocationsUseCase,
                    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    // FOR DATA
    private val _locations = MediatorLiveData<Resource<List<Location>>>()
    val locations: LiveData<Resource<List<Location>>> get() = _locations
    private var moviesSource: LiveData<Resource<List<Location>>> = MutableLiveData()


    // PUBLIC ACTIONS ---
    fun locationClicksOnItem(location: Location) {
        location.key?.let {
            navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }
    }


    fun locationRefreshesItems() = getLocations()

    fun onSearchMenuItemClick() =
        navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())


    fun loadLocations() {
        getLocations()
    }

    // ---

    private fun getLocations() = viewModelScope.launch(dispatchers.main) {
        // We make sure there is only one source of livedata (allowing us properly refresh)
        _locations.removeSource(moviesSource)

        withContext(dispatchers.io) {
            moviesSource = getLocationsUseCase()
        }
        _locations.addSource(moviesSource) {
            _locations.value = it
            if (it.status == Resource.Status.ERROR)
                _snackbarError.value = Event(R.string.text_location_history_is_empty)
        }
    }

}

