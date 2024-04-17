package ru.export.testapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resourceId: Int, vararg values: String?): String
}

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(resourceId: Int, vararg values: String?): String = context.getString(resourceId, *values)
}