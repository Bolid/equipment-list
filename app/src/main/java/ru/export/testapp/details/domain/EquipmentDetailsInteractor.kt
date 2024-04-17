package ru.export.testapp.details.domain

import ru.export.testapp.details.domain.model.EquipmentDetailsModel
import ru.export.testapp.details.repository.EquipmentDetailsRepository

interface EquipmentDetailsInteractor {
    suspend fun getEquipmentDetails(equipmentId: String): EquipmentDetailsModel
}

class EquipmentDetailsInteractorImpl(private val repository: EquipmentDetailsRepository) : EquipmentDetailsInteractor {

    override suspend fun getEquipmentDetails(equipmentId: String): EquipmentDetailsModel {
        return repository.getEquipmentDetails(equipmentId)
    }

}