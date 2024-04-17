package ru.export.testapp.list.repository

import ru.export.testapp.list.data.EquipmentListApi
import ru.export.testapp.list.domain.model.EquipmentItemModel

interface EquipmentListRepository {
    suspend fun getEquipmentList(): List<EquipmentItemModel>
}

class EquipmentListRepositoryImpl(private val api: EquipmentListApi) : EquipmentListRepository {

    override suspend fun getEquipmentList(): List<EquipmentItemModel> {
        return api.getEquipmentList().returnValue.map { it.equipmentResponseMapToModel() }
    }
}