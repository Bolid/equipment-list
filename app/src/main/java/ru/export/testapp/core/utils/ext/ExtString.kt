package ru.export.testapp.core.utils.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATE_PARSE_FORMAT = "yyyy-mm-dd"
fun String?.dateParse(): Date? {
    if (isNullOrEmpty()) return null
    return try {
        SimpleDateFormat(DATE_PARSE_FORMAT, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        null
    }
}