package ru.export.testapp.list.data.response

import com.google.gson.annotations.SerializedName

class EquipmentListResponse(
    val returnValue: List<EquipmentItemResponse>
)

class EquipmentItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("ststus") val status: String,
    @SerializedName("criticality_code") val criticalityCode: String,
    @SerializedName("criticality") val criticality: String,
)

/*
* {
            "id": "65b7cb09-5796-4317-b1a0-2124ded23af0",
            "code": "101014",
            "name": "Конвейер ленточный №4 59747 (отсев ≥12мм) EP 400, B-500, L=19,6 м",
            "status_code": "installed",
            "ststus": "В эксплуатации",
            "criticality_code": "1",
            "criticality": "Очень критично"
        }*/