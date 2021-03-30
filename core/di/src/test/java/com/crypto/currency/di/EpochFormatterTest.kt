package com.crypto.currency.di

import com.crypto.currency.di.mapper.EpochFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.DateTimeException


class EpochFormatterTest {

    @Test
    fun `format Valid Epoch time to TimeStamp correctly`() {
        assertEquals("2015-09-18 00:00:00", EpochFormatter.toYearMonthDayDateFormat(1442534400))
    }

    @Test(expected = DateTimeException::class)
    fun `format Invalid Epoch time to TimeStamp should throw DateTimeException`() {
        assertEquals("2015-09-18 00:00:00", EpochFormatter.toYearMonthDayDateFormat(Long.MAX_VALUE))
    }
}