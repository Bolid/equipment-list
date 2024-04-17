package ru.export.testapp.details.presentation

import androidx.lifecycle.MutableLiveData
import ru.export.testapp.R
import ru.export.testapp.core.BaseViewModel
import ru.export.testapp.core.ErrorEvent
import ru.export.testapp.core.ResourceProvider
import ru.export.testapp.core.utils.ext.simpleFormat
import ru.export.testapp.details.domain.EquipmentDetailsInteractor
import ru.export.testapp.details.presentation.model.EquipmentDetailsViewState
import ru.export.testapp.flow.OnEquipmentItemListener

class EquipmentDetailsViewModel(
    private val interactor: EquipmentDetailsInteractor,
    private val resourceProvider: ResourceProvider,
    private val onEquipmentItemListener: OnEquipmentItemListener
) : BaseViewModel() {

    private val equipmentDetailsMutable: MutableLiveData<EquipmentDetailsViewState> = MutableLiveData()
    val equipmentLiveData = equipmentDetailsMutable

    init {
        onReceiveItem()
    }

    private fun onLaunchEquipmentDetails(equipmentId: String) {
        launch({ events.onNext(ErrorEvent(resourceProvider.getString(R.string.general_error_message))) }) {
            val model = interactor.getEquipmentDetails(equipmentId)
            equipmentDetailsMutable.postValue(
                EquipmentDetailsViewState(
                    code = resourceProvider.getString(R.string.equipment_details_code, model.code),
                    name = model.name,
                    department = model.department.name,
                    status = resourceProvider.getString(R.string.equipment_details_status, model.status.value),
                    hierarchyLevelType = model.hierarchyLevelType.name,
                    costCode = model.costCode.name,

                    inventoryNumber = resourceProvider.getString(
                        R.string.equipment_details_inventory_number,
                        model.inventoryNumber ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    model = resourceProvider.getString(
                        R.string.equipment_details_model,
                        model.model ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    commissDate = resourceProvider.getString(
                        R.string.equipment_details_commiss_date,
                        model.commissDate?.simpleFormat() ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    initialValue = resourceProvider.getString(
                        R.string.equipment_details_initial_value,
                        model.initialValue ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    serialNumber = resourceProvider.getString(
                        R.string.equipment_details_serial_number,
                        model.serialNumber ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    installationDate = resourceProvider.getString(
                        R.string.equipment_details_installation_date,
                        model.installationDate?.simpleFormat() ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    ecology = model.ecology,
                    safety = model.safety,
                    dormantCauseName = resourceProvider.getString(
                        R.string.equipment_details_dormant_cause_name,
                        model.dormantCauseModel?.name ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    dormantStartDate = resourceProvider.getString(
                        R.string.equipment_details_dormant_start_date,
                        model.dormantStartDate?.simpleFormat() ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    dormantEndDate = resourceProvider.getString(
                        R.string.equipment_details_dormant_end_date,
                        model.dormantEndDate?.simpleFormat() ?: resourceProvider.getString(R.string.app_value_unknown)
                    ),
                    enableState = model.status.code != WITHDRAWN_STATUS_VALUE
                )
            )
        }
    }

    private fun onReceiveItem() {
        launch {
            onEquipmentItemListener.clickFlow.collect(::onLaunchEquipmentDetails)
        }
    }

    private companion object {
        const val WITHDRAWN_STATUS_VALUE = "withdrawn"
    }
}