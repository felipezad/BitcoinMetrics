package com.crypto.currency.di.mapper

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object EpochFormatter : ValueFormatter() {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun toYearMonthDayDateFormat(epochTime: Long): String {
        return try {
            val localDateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC)
            localDateTime.format(formatter)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return try {
            toYearMonthDayDateFormat(value.toLong())
        } catch (_: Exception) {
            super.getAxisLabel(value, axis)
        }
    }
}