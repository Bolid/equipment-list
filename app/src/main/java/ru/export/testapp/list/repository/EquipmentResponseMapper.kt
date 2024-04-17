package ru.export.testapp.list.repository

import ru.export.testapp.list.data.response.EquipmentItemResponse
import ru.export.testapp.list.domain.model.CriticalityCode
import ru.export.testapp.list.domain.model.EquipmentItemModel
import ru.export.testapp.list.domain.model.StatusCode

fun EquipmentItemResponse.equipmentResponseMapToModel(): EquipmentItemModel {
    return EquipmentItemModel(
        id = id,
        code = code,
        name = name,
        criticality = criticality,
        status = status,
        statusCode = StatusCode.getStatusCode(statusCode),
        criticalityCode = CriticalityCode.getCriticalityCode(criticalityCode),
    )
}
