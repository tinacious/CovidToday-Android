package com.tinaciousdesign.covidtoday.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tinaciousdesign.covidtoday.data.Country;
import com.tinaciousdesign.covidtoday.data.SortCriteria;
import com.tinaciousdesign.covidtoday.networking.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CountriesRepository {
    private static final String TAG = "CountriesRepository";

    private static CountriesRepository countriesRepository = null;
    private final ApiService mApiService;

    MutableLiveData<List<Country>> countries = new MutableLiveData<>();

    private CountriesRepository() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://diseash.sh/")
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static CountriesRepository getInstance() {
        if (countriesRepository == null) {
            countriesRepository = new CountriesRepository();
        }
        return countriesRepository;
    }

    public void fetchData(SortCriteria sortCriteria) {
        Call<List<Country>> call =  mApiService.getCountries(sortCriteria);
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NotNull Call<List<Country>> call, @NotNull Response<List<Country>> response) {
                countries.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Country>> call, @NotNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public MutableLiveData<List<Country>>getCountries() {
        return this.countries;
    }
}
