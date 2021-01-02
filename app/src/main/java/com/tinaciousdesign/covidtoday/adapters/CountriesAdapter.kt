package com.tinaciousdesign.covidtoday.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinaciousdesign.covidtoday.R
import com.tinaciousdesign.covidtoday.data.Country
import com.tinaciousdesign.covidtoday.databinding.CountryCardBinding
import com.tinaciousdesign.covidtoday.utils.FormattingUtils
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
//            itemView.findViewById<TextView>(R.id.countryName).apply {
//                text = country.country
//            }
//            itemView.findViewById<ImageView>(R.id.countryImage).apply {
//                Glide.with(itemView.context)
//                    .load(country.countryInfo.flag)
//                    .into(this)
//            }
            viewBinding.countryName.text = country.country

            Glide.with(itemView.context)
                .load(country.countryInfo.flag)
                .into(viewBinding.countryImage)

            viewBinding.totalCases.text = FormattingUtils.formatFloatWithThousandsDelimiter(country.cases, userLocale)
            viewBinding.todayCases.text = FormattingUtils.formatFloatWithThousandsDelimiter(country.todayCases, userLocale)
        }
    }
}


