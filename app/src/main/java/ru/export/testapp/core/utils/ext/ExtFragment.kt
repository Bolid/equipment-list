package ru.export.testapp.core.utils.ext

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.findFromParent(): Lazy<T> = lazy {
    var fragment = this
    var searching = true
    while (searching) {
        fragment = fragment.parentFragment ?: break
        if (fragment is T) searching = false
    }
    fragment as? T
        ?: activity as? T
        ?: throw IllegalStateException("Interface not found among parents")
}