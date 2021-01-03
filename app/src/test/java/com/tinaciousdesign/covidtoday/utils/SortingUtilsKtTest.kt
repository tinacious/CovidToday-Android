package com.tinaciousdesign.covidtoday.utils

import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.CountryInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class SortingUtilsKtTest {
    private val country1 = Country(
        active = 100f,
        cases = 1111.0f,
        critical = 200.0f,
        deaths = 400f,
        country = "Narnia",
        countryInfo = CountryInfo(flag = "https://example.com/flag.png", _id = 1),
        todayCases = 1000f,
        todayRecovered = 20f,
        todayDeaths = 10f,
        activePerOneMillion = 12f,
        deathsPerOneMillion = 100f,
        recovered = 1000f,
        criticalPerOneMillion = 100f,
        casesPerOneMillion = 100f,
        recoveredPerOneMillion = 100f
    )
    private val country2 = Country(
        active = 100f,
        cases = 123456.0f,
        critical = 200.0f,
        deaths = 400f,
        country = "Wonderland",
        countryInfo = CountryInfo(flag = "https://example.com/flag.png", _id = 2),
        todayCases = 1200f,
        todayRecovered = 20f,
        todayDeaths = 2f,
        activePerOneMillion = 12f,
        deathsPerOneMillion = 100f,
        recovered = 1000f,
        criticalPerOneMillion = 100f,
        casesPerOneMillion = 100f,
        recoveredPerOneMillion = 100f
    )
    private val country3 = Country(
        active = 100f,
        cases = 2000.0f,
        critical = 200.0f,
        deaths = 400f,
        country = "Wakanda",
        countryInfo = CountryInfo(flag = "https://example.com/flag.png", _id = 2),
        todayCases = 10f,
        todayRecovered = 20f,
        todayDeaths = 0f,
        activePerOneMillion = 12f,
        deathsPerOneMillion = 100f,
        recovered = 1000f,
        criticalPerOneMillion = 100f,
        casesPerOneMillion = 100f,
        recoveredPerOneMillion = 100f
    )
    val input = listOf(country1, country2, country3)

    @Test
    fun `when position = 0, sorts by cases today`() {
        val position = 0
        val result = sortCountriesForTab(input, position)

        assertEquals("Wonderland", result[0].country)
        assertEquals("Narnia", result[1].country)
        assertEquals("Wakanda", result[2].country)
    }

    @Test
    fun `when position = 1, sorts by deaths today`() {
        val position = 1
        val result = sortCountriesForTab(input, position)

        assertEquals("Narnia", result[0].country)
        assertEquals("Wonderland", result[1].country)
        assertEquals("Wakanda", result[2].country)
    }

    @Test
    fun `when position = 2, sorts by total cases`() {
        val position = 2
        val result = sortCountriesForTab(input, position)

        assertEquals("Wonderland", result[0].country)
        assertEquals("Wakanda", result[1].country)
        assertEquals("Narnia", result[2].country)
    }
}
