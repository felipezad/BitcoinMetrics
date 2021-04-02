package com.crypto.currency.di.mapper

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object EpochFormatter {

    private val formatterYear: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val formatterMonth: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd")

    fun toYearMonthDayDateFormat(epochTime: Long): String {
        return try {
            val localDateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC)
            localDateTime.format(formatterYear)
        } catch (e: Exception) {
            throw e
        }
    }

    fun toMonthDayDateFormat(epochTime: Long): String {
        return try {
            val localDateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC)
            localDateTime.format(formatterMonth)
        } catch (e: Exception) {
            throw e
        }
    }
}