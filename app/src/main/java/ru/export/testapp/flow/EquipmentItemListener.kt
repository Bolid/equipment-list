package ru.export.testapp.flow

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface OnEquipmentItemListener {
    val clickFlow: SharedFlow<String>
    suspend fun onClick(equipmentId: String)
}

class OnEquipmentItemListenerImpl : OnEquipmentItemListener {

    private val _clickFlow = MutableSharedFlow<String>()

    override val clickFlow: SharedFlow<String> = _clickFlow.asSharedFlow()

    override suspend fun onClick(equipmentId: String) {
        _clickFlow.emit(equipmentId)
    }
}