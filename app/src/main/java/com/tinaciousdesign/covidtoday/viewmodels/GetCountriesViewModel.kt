package com.tinaciousdesign.covidtoday.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.SortCriteria
import com.tinaciousdesign.covidtoday.services.CountriesRepository

class GetCountriesViewModel: ViewModel() {
    private val repository = CountriesRepository.getInstance()

    init {
        repository.fetchData(SortCriteria.TodayCases)
    }

    val countries: LiveData<List<Country>>
        get() = repository.countries
}
