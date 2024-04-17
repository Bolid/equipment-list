package ru.export.testapp.details.data

import retrofit2.http.Body
import retrofit2.http.POST
import ru.export.testapp.details.data.request.EquipmentDetailsRequest
import ru.export.testapp.details.data.response.EquipmentDetailsValueResponse

interface EquipmentDetailsApi {
    @POST("platform/api/dm/rest/noAuth/actions/22bedbbd-9b7b-43d3-8f2f-e53f90b1faf9/run")
    suspend fun getEquipmentDetails(@Body request: EquipmentDetailsRequest): EquipmentDetailsValueResponse
}