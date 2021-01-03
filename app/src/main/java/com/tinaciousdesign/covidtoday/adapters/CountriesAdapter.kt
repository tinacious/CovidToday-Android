package com.tinaciousdesign.covidtoday.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.databinding.CountryCardBinding
import com.tinaciousdesign.covidtoday.utils.formatFloatForLocale
import java.util.*

class CountriesAdapter() : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {
    var data = mutableListOf<Country>()

    private lateinit var viewBinding: CountryCardBinding

    fun updateData(newData: List<Country>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        viewBinding = CountryCardBinding.inflate(LayoutInflater.from(parent.context))
        val itemView = viewBinding.root
        return CountriesViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: CountriesViewHolder, position: Int) {
        val country = data[position]
        viewHolder.bind(country)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long {
        val country = data[position]
        return country.countryInfo._id.toLong()
    }

    override fun getItemViewType(position: Int): Int = position

    companion object {
        private const val TAG = "CountriesAdapter"
    }


    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {
            val userLocale: Locale = ConfigurationCompat.getLocales(itemView.resources.configuration)[0]
            viewBinding.countryName.text = country.country

            Glide.with(itemView.context)
                .load(country.countryInfo.flag)
                .into(viewBinding.countryImage)

            viewBinding.totalCases.text = formatFloatForLocale(country.cases, userLocale)
            viewBinding.todayCases.text = formatFloatForLocale(country.todayCases, userLocale)
            viewBinding.deaths.text = formatFloatForLocale(country.deaths, userLocale)
            viewBinding.todayDeaths.text = formatFloatForLocale(country.todayDeaths, userLocale)
            viewBinding.recovered.text = formatFloatForLocale(country.recovered, userLocale)
            viewBinding.active.text = formatFloatForLocale(country.active, userLocale)
            viewBinding.critical.text = formatFloatForLocale(country.critical, userLocale)
            viewBinding.casesPerOneMillion.text = formatFloatForLocale(country.casesPerOneMillion, userLocale)
            viewBinding.deathsPerOneMillion.text = formatFloatForLocale(country.deathsPerOneMillion, userLocale)
        }
    }
}


