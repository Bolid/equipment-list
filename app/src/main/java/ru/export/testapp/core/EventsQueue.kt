package ru.export.testapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.util.LinkedList
import java.util.Queue

/**
 * Реализация LiveData, которая выполняет события один раз.
 */
class EventsQueue<T> : MutableLiveData<Queue<T>>() {

    fun onNext(value: T) {
        val events = getValue() ?: LinkedList()
        events.add(value)
        setValue(events)
    }
}

inline fun EventsQueue<Event>.observeEvents(
    owner: LifecycleOwner,
    crossinline eventHandler: (Event) -> Unit
) {
    observe(owner) { queue: Queue<Event>? ->
        while (queue != null && queue.isNotEmpty()) {
            eventHandler(queue.remove())
        }
    }
}

interface Event

class ErrorEvent(val errorMessage: String) : Event
