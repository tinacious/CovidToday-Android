package com.tinaciousdesign.covidtoday.utils

import androidx.recyclerview.widget.DiffUtil
import com.tinaciousdesign.covidtoday.data.Country

/**
 * For comparing adapter data
 */
class CountriesDiffUtilCallback(
    private val newList: List<Country>,
    private val oldList: List<Country>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.countryInfo._id == newItem.countryInfo._id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.countryInfo._id == newItem.countryInfo._id
    }
}
