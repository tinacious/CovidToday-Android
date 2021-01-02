package com.tinaciousdesign.covidtoday.networking

import com.tinaciousdesign.covidtoday.data.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/v2/countries")
    fun getCountries(@Query("sort") sortCriteria: String): Call<List<Country>>
}



