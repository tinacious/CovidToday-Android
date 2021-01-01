package com.tinaciousdesign.covidtoday.data

import com.tinaciousdesign.covidtoday.networking.CountryInfoResponse

data class Country(
    val active: Int,
    val activePerOneMillion: Int,
    val cases: Int,
    val casesPerOneMillion: Int,
    val critical: Int,
    val criticalPerOneMillion: Int,
    val deaths: Int,
    val deathsPerOneMillion: Int,
    val recovered: Int,
    val recoveredPerOneMillion: Int,
    val country: String,
    val countryInfo: CountryInfoResponse,
    val todayDeaths: Int,
    val todayCases: Int,
    val todayRecovered: Int,
//    val updated: // Epoch time stamp
)
