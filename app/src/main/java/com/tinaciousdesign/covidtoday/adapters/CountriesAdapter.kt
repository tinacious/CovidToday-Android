package com.tinaciousdesign.covidtoday.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.databinding.CountryCardBinding
import com.tinaciousdesign.covidtoday.utils.formatFloatWithThousandsDelimiter
import java.util.*

class CountriesAdapter() : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {
    var data = mutableListOf<Country>()

    private lateinit var viewBinding: CountryCardBinding
    //    private lateinit var mCountryNameView: TextView
//    private lateinit var mCountryImageView: ImageView

    fun updateData(newData: List<Country>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        viewBinding = CountryCardBinding.inflate(LayoutInflater.from(parent.context))
        val itemView = viewBinding.root

//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.country_card, parent, false)

        return CountriesViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: CountriesViewHolder, position: Int) {
        val country = data[position]
        viewHolder.bind(country)
    }

    override fun getItemCount(): Int = data.size


    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {
            val userLocale: Locale = ConfigurationCompat.getLocales(itemView.resources.configuration)[0]
            viewBinding.countryName.text = country.country

            Glide.with(itemView.context)
                .load(country.countryInfo.flag)
                .into(viewBinding.countryImage)

            viewBinding.totalCases.text = formatFloatWithThousandsDelimiter(country.cases, userLocale)
            viewBinding.todayCases.text = formatFloatWithThousandsDelimiter(country.todayCases, userLocale)
            viewBinding.deaths.text = formatFloatWithThousandsDelimiter(country.deaths, userLocale)
            viewBinding.recovered.text = formatFloatWithThousandsDelimiter(country.recovered, userLocale)
            viewBinding.active.text = formatFloatWithThousandsDelimiter(country.active, userLocale)
            viewBinding.critical.text = formatFloatWithThousandsDelimiter(country.critical, userLocale)
            viewBinding.casesPerOneMillion.text = formatFloatWithThousandsDelimiter(country.casesPerOneMillion, userLocale)
            viewBinding.deathsPerOneMillion.text = formatFloatWithThousandsDelimiter(country.deathsPerOneMillion, userLocale)
        }
    }
}


