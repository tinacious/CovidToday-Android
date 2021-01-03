package com.tinaciousdesign.covidtoday.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.data.SortCriteria
import com.tinaciousdesign.covidtoday.networking.ApiClient
import com.tinaciousdesign.covidtoday.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class CountriesRepository private constructor() {
    private val mApiService: ApiService
    var mutableLiveData = MutableLiveData<List<Country>>()

    fun getMutableLiveData(sortCriteria: SortCriteria): MutableLiveData<List<Country>> {
        val call = mApiService.getCountries(sortCriteria.stringValue)
        call.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                val countries = response.body() as ArrayList<Country>
                mutableLiveData.value = countries
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e(TAG, t.message!!)
            }
        })
        return mutableLiveData
    }

    companion object {
        private const val TAG = "CountriesRepository"
        private var countriesRepository: CountriesRepository? = null

        val instance: CountriesRepository
            get() {
                if (countriesRepository == null) {
                    countriesRepository = CountriesRepository()
                }
                return countriesRepository!!
            }
    }

    init {
        val retrofit = ApiClient.getInstance()
        mApiService = retrofit.create(ApiService::class.java)
    }
}
