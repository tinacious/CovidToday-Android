package com.tinaciousdesign.covidtoday.data

enum class SortCriteria(private val value: String) {
    Cases("cases"),
    TodayCases("todayCases"),
    Deaths("deaths"),
    TodayDeaths("todayDeaths"),
    Recovered("recovered"),
    Active("active"),
    Critical("critical"),
    CasesPerMillion("casesPerOneMillion"),
    DeathsPerMillion("deathsPerOneMillion"),
}
