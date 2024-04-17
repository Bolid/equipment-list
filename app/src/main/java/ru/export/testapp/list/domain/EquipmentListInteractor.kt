package ru.export.testapp.list.domain

import ru.export.testapp.list.domain.model.EquipmentItemModel
import ru.export.testapp.list.repository.EquipmentListRepository

interface EquipmentListInteractor {
    suspend fun getEquipmentList(): List<EquipmentItemModel>
}

class EquipmentListInteractorImpl(private val repository: EquipmentListRepository) : EquipmentListInteractor {

    override suspend fun getEquipmentList(): List<EquipmentItemModel> {
        return repository.getEquipmentList()
    }

}