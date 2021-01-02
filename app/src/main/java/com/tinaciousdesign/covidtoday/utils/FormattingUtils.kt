package com.tinaciousdesign.covidtoday.utils

import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt


fun formatFloatWithThousandsDelimiter(value: Float, locale: Locale): String {
    val numberFormat = NumberFormat.getInstance(locale)
    val intValue = value.roundToInt()

    return numberFormat.format(intValue)
}
