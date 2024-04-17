package ru.export.testapp.list.data

import retrofit2.http.POST
import ru.export.testapp.list.data.response.EquipmentListResponse

interface EquipmentListApi {
    @POST("platform/api/dm/rest/noAuth/actions/caede3a6-d7e5-4a50-b283-4b20b07eb3fb/run")
    suspend fun getEquipmentList(): EquipmentListResponse
}