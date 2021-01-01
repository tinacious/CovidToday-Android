package com.tinaciousdesign.covidtoday.viewmodels

import android.util.Log
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

//    val countries: MutableLiveData<List<Country>> by lazy {
//        MutableLiveData<List<Country>>()
//    }

//    private var _countries = LiveData<List<Country>>
    private val repository = CountriesRepository.getInstance()

    init {
        Log.d(TAG, "Calling init")
//        repository.fetchData(SortCriteria.TodayCases)
        getCountries(SortCriteria.TodayCases)
    }

//    private val _countries = LiveData<List<Country>>()
//    fun getCountries(sortCriteria: SortCriteria): LiveData<List<Country>> {
    fun getCountries(sortCriteria: SortCriteria) {
        _countries = repository.getCountries(sortCriteria)
    }

//    val countries: LiveData<List<Country>>
//        get() = repository.countries

    companion object {
        private const val TAG = "MainViewModel"
    }
}
