package ru.export.testapp.filter_list.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EquipmentFilterParam(val criticalityList: List<CriticalityParam>) : Parcelable {

    @Parcelize
    class CriticalityParam(val name: String, var checked: Boolean) : Parcelable
}