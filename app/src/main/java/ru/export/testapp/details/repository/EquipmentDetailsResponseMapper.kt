package ru.export.testapp.details.repository

import ru.export.testapp.core.utils.ext.dateParse
import ru.export.testapp.details.data.response.EquipmentDetailsResponse
import ru.export.testapp.details.domain.model.EquipmentDetailsCostCodeModel
import ru.export.testapp.details.domain.model.EquipmentDetailsDepartmentModel
import ru.export.testapp.details.domain.model.EquipmentDetailsDormantCauseModel
import ru.export.testapp.details.domain.model.EquipmentDetailsHierarchyLevelTypeModel
import ru.export.testapp.details.domain.model.EquipmentDetailsModel
import ru.export.testapp.details.domain.model.EquipmentDetailsStatusModel

fun EquipmentDetailsResponse.equipmentDetailsResponseMapToModel(): EquipmentDetailsModel {
    return EquipmentDetailsModel(
        code = code,
        name = name,
        department = EquipmentDetailsDepartmentModel(department.name),
        status = EquipmentDetailsStatusModel(status.value, status.code),
        hierarchyLevelType = EquipmentDetailsHierarchyLevelTypeModel(hierarchyLevelType.name),
        costCode = EquipmentDetailsCostCodeModel(costCode.name),
        dormantCauseModel = dormantCauseResponse?.name?.let { EquipmentDetailsDormantCauseModel(it) },
        inventoryNumber = inventoryNumber,
        model = model,
        commissDate = commissDate.dateParse(),
        initialValue = initialValue,
        serialNumber = serialNumber,
        installationDate = installationDate.dateParse(),
        ecology = ecology,
        safety = safety,
        dormantStartDate = dormantStartDate.dateParse(),
        dormantEndDate = dormantEndDate.dateParse(),
    )
}
