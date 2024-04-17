package ru.export.testapp.details.data.response

import com.google.gson.annotations.SerializedName

class EquipmentDetailsValueResponse(
    @SerializedName("returnValue") val returnValue: EquipmentDetailsResponse
)

class EquipmentDetailsResponse(
    @SerializedName("department") val department: EquipmentDetailsDepartmentResponse,
    @SerializedName("status") val status: EquipmentDetailsStatusResponse,
    @SerializedName("HierarchyLevelType") val hierarchyLevelType: EquipmentDetailsHierarchyLevelTypeResponse,
    @SerializedName("costCode") val costCode: EquipmentDetailsCostCodeResponse,
    @SerializedName("dormantCause") val dormantCauseResponse: EquipmentDetailsDormantCauseResponse?,
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("inventoryNumber") val inventoryNumber: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("commissDate") val commissDate: String,
    @SerializedName("initialValue") val initialValue: String?,
    @SerializedName("serialNumber") val serialNumber: String?,
    @SerializedName("installationDate") val installationDate: String?,
    @SerializedName("ecology") val ecology: Boolean,
    @SerializedName("safety") val safety: Boolean,
    @SerializedName("dormantStartDate") val dormantStartDate: String?,
    @SerializedName("dormantEndDate") val dormantEndDate: String?,

    )

class EquipmentDetailsDepartmentResponse(
    @SerializedName("name") val name: String
)

class EquipmentDetailsStatusResponse(
    @SerializedName("value") val value: String,
    @SerializedName("code") val code: String,
)

class EquipmentDetailsHierarchyLevelTypeResponse(
    @SerializedName("name") val name: String
)

class EquipmentDetailsCostCodeResponse(
    @SerializedName("name") val name: String
)

class EquipmentDetailsDormantCauseResponse(
    @SerializedName("name") val name: String
)
