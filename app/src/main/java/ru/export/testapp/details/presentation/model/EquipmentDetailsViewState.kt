package ru.export.testapp.details.presentation.model

class EquipmentDetailsViewState(
    val code: String,
    val name: String,
    val department: String,
    val status: String,
    val hierarchyLevelType: String,
    val costCode: String,

    val inventoryNumber: String,
    val model: String,
    val commissDate: String,
    val initialValue: String,
    val serialNumber: String,
    val installationDate: String,

    val ecology: Boolean,
    val safety: Boolean,
    val dormantCauseName: String,
    val dormantStartDate: String,
    val dormantEndDate: String,

    val enableState: Boolean
)