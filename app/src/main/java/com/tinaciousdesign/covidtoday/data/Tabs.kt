package com.tinaciousdesign.covidtoday.data

import android.content.Context
import com.tinaciousdesign.covidtoday.R

fun getTabs(context: Context): List<String> {
    return listOf(
        context.getString(R.string.tab_todayCases),
        context.getString(R.string.tab_todayDeaths),
        context.getString(R.string.tab_cases),
        context.getString(R.string.tab_deaths),
        context.getString(R.string.tab_recovered),
        context.getString(R.string.tab_active),
        context.getString(R.string.tab_critical),
        context.getString(R.string.tab_casesPerOneMillion),
        context.getString(R.string.tab_deathsPerOneMillion)
    )
}
