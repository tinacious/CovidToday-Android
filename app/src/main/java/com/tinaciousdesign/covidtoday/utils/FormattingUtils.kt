package com.tinaciousdesign.covidtoday.utils

import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt


fun formatFloatForLocale(value: Float, locale: Locale): String {
    val numberFormat = NumberFormat.getInstance(locale)
    val intValue = value.roundToInt()

    return numberFormat.format(intValue)
}
