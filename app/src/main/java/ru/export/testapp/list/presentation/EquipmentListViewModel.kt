package ru.export.testapp.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.export.testapp.R
import ru.export.testapp.core.BaseViewModel
import ru.export.testapp.core.ErrorEvent
import ru.export.testapp.core.ResourceProvider
import ru.export.testapp.filter_list.model.EquipmentFilterParam
import ru.export.testapp.flow.OnEquipmentItemListener
import ru.export.testapp.list.domain.EquipmentListInteractor
import ru.export.testapp.list.domain.model.CriticalityCode
import ru.export.testapp.list.domain.model.EquipmentItemModel
import ru.export.testapp.list.domain.model.StatusCode
import ru.export.testapp.list.presentation.model.EquipmentEvent
import ru.export.testapp.list.presentation.model.EquipmentViewItem

class EquipmentListViewModel(
    private val interactor: EquipmentListInteractor,
    private val onEquipmentItemListener: OnEquipmentItemListener,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val originModel: MutableList<EquipmentItemModel> = mutableListOf()
    private var filterParams: EquipmentFilterParam? = null
    private val equipmentItemsItems = MutableLiveData<List<EquipmentViewItem>>()
    val equipmentLiveData: LiveData<List<EquipmentViewItem>> = equipmentItemsItems

    init {
        launchEquipmentList()
    }

    fun doFilter() {
        val filter = filterParams ?: EquipmentFilterParam(originModel
            .map { it.criticality }
            .toSet()
            .map { EquipmentFilterParam.CriticalityParam(it, true) })
            .also { filterParams = it }
        events.onNext(EquipmentEvent.EquipmentFilterEvent(filter))
    }

    fun onFilter(params: EquipmentFilterParam) {
        filterParams = params
        val available = params.criticalityList
            .filter { it.checked }
            .map { it.name }
        val newList = originModel
            .filter { available.contains(it.criticality) }
            .mapToEquipmentViewItem()
        events.onNext(EquipmentEvent.EquipmentFilteredElementEvent(newList))
    }

    private fun launchEquipmentList() {
        launch({ events.onNext(ErrorEvent(resourceProvider.getString(R.string.general_error_message))) }) {
            val list = interactor.getEquipmentList()
            originModel.addAll(list)
            equipmentItemsItems.postValue(list.mapToEquipmentViewItem())
        }
    }

    private fun onItemClick(code: String) {
        val equipmentId = originModel.find { it.code == code }?.id ?: return
        launch { onEquipmentItemListener.onClick(equipmentId) }
    }

    private fun List<EquipmentItemModel>.mapToEquipmentViewItem() = map {
        EquipmentViewItem(
            code = it.code,
            name = it.name,
            criticality = it.criticality,
            status = it.status,
            statusBackground = when (it.statusCode) {
                StatusCode.INSTALLED -> R.color.color_installed
                StatusCode.WITHDRAWN -> R.color.color_withdrawn
                StatusCode.STATUS_UNKNOWN -> 0
            },
            criticalityBackground = when (it.criticalityCode) {
                CriticalityCode.CRITICALITY_1 -> R.color.color_criticality_one
                CriticalityCode.CRITICALITY_3 -> R.color.color_criticality_three
                CriticalityCode.CRITICALITY_4 -> R.color.color_criticality_four
                CriticalityCode.CRITICALITY_5 -> R.color.color_criticality_five
                CriticalityCode.CRITICALITY_UNKNOWN -> 0
            },
            onClick = ::onItemClick
        )
    }

}