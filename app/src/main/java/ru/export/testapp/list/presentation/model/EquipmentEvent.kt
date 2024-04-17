package ru.export.testapp.list.presentation.model

import ru.export.testapp.core.Event
import ru.export.testapp.filter_list.model.EquipmentFilterParam


sealed interface EquipmentEvent : Event {
    class EquipmentFilterEvent(val filterParams: EquipmentFilterParam) : EquipmentEvent
    class EquipmentFilteredElementEvent(val list: List<EquipmentViewItem>) : EquipmentEvent
}
