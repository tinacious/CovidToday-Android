package com.tinaciousdesign.covidtoday.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.SortCriteria
import com.tinaciousdesign.covidtoday.services.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

//    private var _tabCountriesLookup: MutableLiveData<Map<Int, List<Country>>?>() = null
//    private var _tabCountriesLookup: Map<Int, List<Country>>? = null
//    val tabCountriesLookup: Map<Int, List<Country>>?
//        get() = _tabCountriesLookup

//    val countries: MutableLiveData<List<Country>> by lazy {
//        MutableLiveData<List<Country>>()
//    }

//    private var _countries = LiveData<List<Country>>
    private val repository = CountriesRepository.getInstance()

    init {
        fetchCountriesForSortCriteria(SortCriteria.TodayCases)
    }

//    private val _countries = LiveData<List<Country>>()
//    fun getCountries(sortCriteria: SortCriteria): LiveData<List<Country>> {
    fun fetchCountriesForSortCriteria(sortCriteria: SortCriteria): LiveData<List<Country>> {
        _countries = repository.getMutableLiveData(sortCriteria)
        return _countries
    }

//    val countries: LiveData<List<Country>>
//        get() = repository.countries

    companion object {
        private const val TAG = "MainViewModel"
    }
}
