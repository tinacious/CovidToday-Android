package com.tinaciousdesign.covidtoday.utils

import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.TabIndex


fun sortCountriesForTab(countries: List<Country>, position: Int): List<Country> =
    when (position) {
        TabIndex.CASES_TODAY.ordinal -> countries.sortedByDescending { it.todayCases }
        TabIndex.DEATHS_TODAY.ordinal -> countries.sortedByDescending { it.todayDeaths }
        TabIndex.ALL_CASES.ordinal -> countries.sortedByDescending { it.cases }
        TabIndex.ALL_DEATHS.ordinal -> countries.sortedByDescending { it.deaths }
        TabIndex.RECOVERED.ordinal -> countries.sortedByDescending { it.recovered }
        TabIndex.ACTIVE.ordinal -> countries.sortedByDescending { it.active }
        TabIndex.CRITICAL.ordinal -> countries.sortedByDescending { it.critical }
        TabIndex.CASES_PER_MILLION.ordinal -> countries.sortedByDescending { it.casesPerOneMillion }
        TabIndex.DEATHS_PER_MILLION.ordinal -> countries.sortedByDescending { it.deathsPerOneMillion }
        else -> countries
    }
