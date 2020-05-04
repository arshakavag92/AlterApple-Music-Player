package com.arshak.core.extensions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


object KotlinExtensions {

    private const val DEFAULT_DATE_INPUT_FORMATTER = "yyyy-MM-dd"
    private const val DEFAULT_DATE_OUTPUT_FORMATTER = "dd MMM, yyyy"
    private const val DATE_YEAR_OUTPUT_FORMATTER = "yyyy"

    fun Long.toMinutes() = ((this / 1000) / 60).toInt()

    fun String.toFormattedDate(): String {
        val formatter = DateTimeFormat.forPattern(DEFAULT_DATE_INPUT_FORMATTER)
        val returnFormatter = DateTimeFormat.forPattern(DEFAULT_DATE_OUTPUT_FORMATTER)
        val dt: DateTime = formatter.parseDateTime(this)
        return dt.toString(returnFormatter)
    }

    fun String.toFormattedYear(): String {
        val formatter: DateTimeFormatter = DateTimeFormat.forPattern(DEFAULT_DATE_INPUT_FORMATTER)
        val returnFormatter = DateTimeFormat.forPattern(DATE_YEAR_OUTPUT_FORMATTER)
        val dt: DateTime = formatter.parseDateTime(this)
        return dt.toString(returnFormatter)
    }
}