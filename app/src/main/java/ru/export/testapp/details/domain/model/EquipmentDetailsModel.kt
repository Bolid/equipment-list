package ru.export.testapp.details.domain.model

import java.util.Date

class EquipmentDetailsModel(
    val code: String,
    val name: String,
    val department: EquipmentDetailsDepartmentModel,
    val status: EquipmentDetailsStatusModel,
    val hierarchyLevelType: EquipmentDetailsHierarchyLevelTypeModel,
    val costCode: EquipmentDetailsCostCodeModel,
    val dormantCauseModel: EquipmentDetailsDormantCauseModel?,
    val inventoryNumber: String?,
    val model: String?,
    val commissDate: Date?,
    val initialValue: String?,
    val serialNumber: String?,
    val installationDate: Date?,
    val ecology: Boolean,
    val safety: Boolean,
    val dormantStartDate: Date?,
    val dormantEndDate: Date?,
)

class EquipmentDetailsDepartmentModel(
    val name: String
)

class EquipmentDetailsStatusModel(
    val value: String,
    val code: String
)

class EquipmentDetailsHierarchyLevelTypeModel(
    val name: String
)

class EquipmentDetailsCostCodeModel(
    val name: String
)

class EquipmentDetailsDormantCauseModel(
    val name: String
)

