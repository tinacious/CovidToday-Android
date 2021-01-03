package com.tinaciousdesign.covidtoday.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.SortCriteria
import com.tinaciousdesign.covidtoday.services.CountriesRepository

class MainViewModel: ViewModel() {

    private var _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    private val repository = CountriesRepository.instance

    init {
        fetchCountriesForSortCriteria(SortCriteria.TodayCases)
    }

    /**
     * Fetches new data from the API for the provided sort criteria. Private, hardcoded now,
     * but later may want to do pull-to-refresh on any tab and make sure the data that shows up is accurate.
     */
    private fun fetchCountriesForSortCriteria(sortCriteria: SortCriteria): LiveData<List<Country>> {
        _countries = repository.getMutableLiveData(sortCriteria)
        return _countries
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
