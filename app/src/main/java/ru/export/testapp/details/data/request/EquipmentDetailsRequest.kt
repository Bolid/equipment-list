package ru.export.testapp.details.data.request

import com.google.gson.annotations.SerializedName

class EquipmentDetailsRequest(
    @SerializedName("obj") val id: String
)