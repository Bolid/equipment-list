package ru.export.testapp.core.utils.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val SIMPLE_PATTERN = "dd MMMM yyyy"
fun Date.simpleFormat(): String = SimpleDateFormat(SIMPLE_PATTERN, Locale.getDefault()).format(this)