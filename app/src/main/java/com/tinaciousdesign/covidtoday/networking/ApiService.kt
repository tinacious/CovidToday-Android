package com.tinaciousdesign.covidtoday.networking

import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.SortCriteria
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/countries")
    fun getCountries(@Query("sort") sortCriteria: SortCriteria): Call<List<Country>>
}



