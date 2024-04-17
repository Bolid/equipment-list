package ru.export.testapp.flow.di

import org.koin.dsl.module
import ru.export.testapp.flow.OnEquipmentItemListener
import ru.export.testapp.flow.OnEquipmentItemListenerImpl

val equipmentFlowModule = module {
    single<OnEquipmentItemListener> {
        OnEquipmentItemListenerImpl()
    }
}