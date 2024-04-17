package ru.export.testapp.list.presentation.model

import androidx.annotation.ColorRes

class EquipmentViewItem(
    val code: String,
    val name: String,
    val criticality: String,
    val status: String,
    @ColorRes val statusBackground: Int,
    @ColorRes val criticalityBackground: Int,
    val onClick: (String) -> Unit
)