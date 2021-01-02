package com.tinaciousdesign.covidtoday.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class FormattingUtilsTest {
    @Test
    fun `given a number, will format it with thousands delimiters`() {
        assertEquals("1,000", formatFloatWithThousandsDelimiter(1000.0.toFloat(), Locale.ENGLISH))
        assertEquals("1.337", formatFloatWithThousandsDelimiter(1337.0.toFloat(), Locale.ITALY))
    }

    @Test
    fun `when given a non-whole number, will round up`() {
        assertEquals("123,456,792", formatFloatWithThousandsDelimiter(123456789.1.toFloat(), Locale.ENGLISH))
    }
}
