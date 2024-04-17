package ru.export.testapp.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val events = EventsQueue<Event>()

    protected inline fun launch(
        crossinline errorHandler: (Throwable) -> Unit = {},
        crossinline procedure: suspend (CoroutineScope) -> Unit,
    ): Job {
        val handler = CoroutineExceptionHandler { _, throwable -> errorHandler(throwable) }
        return viewModelScope.launch(handler) {
            procedure(this)
        }
    }
}