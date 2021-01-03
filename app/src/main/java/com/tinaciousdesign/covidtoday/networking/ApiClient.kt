package com.tinaciousdesign.covidtoday.networking

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private var mRetrofit: Retrofit? = null
        var httpClient: OkHttpClient? = null
        private val API_BASE_URL = "https://disease.sh/v2/"

        @JvmStatic
        fun getInstance(): Retrofit? {
            if (mRetrofit == null) {
                val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
                mRetrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(initOkHttp())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return mRetrofit
        }

        private fun initOkHttp(): OkHttpClient {
            if (httpClient == null) {
                httpClient = OkHttpClient.Builder().build()
            }

            return httpClient as OkHttpClient
        }
    }
}
