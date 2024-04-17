package ru.export.testapp.details.repository

import ru.export.testapp.details.data.EquipmentDetailsApi
import ru.export.testapp.details.data.request.EquipmentDetailsRequest
import ru.export.testapp.details.domain.model.EquipmentDetailsModel

interface EquipmentDetailsRepository {
    suspend fun getEquipmentDetails(equipmentId: String): EquipmentDetailsModel
}

class EquipmentDetailsRepositoryImpl(private val api: EquipmentDetailsApi) : EquipmentDetailsRepository {

    override suspend fun getEquipmentDetails(equipmentId: String): EquipmentDetailsModel {
        return api.getEquipmentDetails(EquipmentDetailsRequest(equipmentId)).returnValue.equipmentDetailsResponseMapToModel()
    }
}