package com.crypto.currency.di.mapper

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object EpochFormatter {

    fun toYearMonthDayDateFormat(epochTime: Long): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val localDateTime = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC)
            localDateTime.format(formatter)
        } catch (e: Exception) {
            throw e
        }
    }
}