package com.tinaciousdesign.covidtoday.data

data class Country(
    val active: Float,
    val activePerOneMillion: Float,
    val cases: Float,
    val casesPerOneMillion: Float,
    val critical: Float,
    val criticalPerOneMillion: Float,
    val deaths: Float,
    val deathsPerOneMillion: Float,
    val recovered: Float,
    val recoveredPerOneMillion: Float,
    val country: String,
    val countryInfo: CountryInfo,
    val todayDeaths: Float,
    val todayCases: Float,
    val todayRecovered: Float,
//    val updated: // Epoch time stamp
)

data class CountryInfo(val flag: String, val _id: Int)
