package ru.export.testapp.details.presentation

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.export.testapp.R
import ru.export.testapp.core.BaseFragment
import ru.export.testapp.core.observeEvents
import ru.export.testapp.core.view.viewBinding
import ru.export.testapp.databinding.EquipmentDetailsFragmentBinding
import ru.export.testapp.details.di.equipmentDetailsModule
import ru.export.testapp.details.presentation.model.EquipmentDetailsViewState

class EquipmentDetailsFragment : BaseFragment(R.layout.equipment_details_fragment) {

    private val equipmentDetailsViewModel: EquipmentDetailsViewModel by viewModel()
    private val binding by viewBinding(EquipmentDetailsFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(equipmentDetailsModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equipmentDetailsViewModel.equipmentLiveData.observe(viewLifecycleOwner, ::onEquipmentDetails)
        equipmentDetailsViewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(equipmentDetailsModule)
    }

    private fun onEquipmentDetails(equipmentDetailsViewState: EquipmentDetailsViewState) {
        binding.editDepartmentName.setText(equipmentDetailsViewState.department)
        binding.editName.setText(equipmentDetailsViewState.name)
        binding.editHierarchyName.setText(equipmentDetailsViewState.hierarchyLevelType)
        binding.editCostCodeName.setText(equipmentDetailsViewState.costCode)
        binding.checkEcology.isChecked = equipmentDetailsViewState.ecology
        binding.checkSafety.isChecked = equipmentDetailsViewState.safety

        binding.code.text = equipmentDetailsViewState.code
        binding.status.text = equipmentDetailsViewState.status
        binding.inventoryNumber.text = equipmentDetailsViewState.inventoryNumber
        binding.model.text = equipmentDetailsViewState.model
        binding.commissDate.text = equipmentDetailsViewState.commissDate
        binding.initialValue.text = equipmentDetailsViewState.initialValue
        binding.serialNumber.text = equipmentDetailsViewState.serialNumber
        binding.installationDate.text = equipmentDetailsViewState.installationDate
        binding.dormantCauseName.text = equipmentDetailsViewState.dormantCauseName
        binding.dormantStartDate.text = equipmentDetailsViewState.dormantStartDate
        binding.dormantEndDate.text = equipmentDetailsViewState.dormantEndDate

        binding.editDepartmentName.isEnabled = equipmentDetailsViewState.enableState
        binding.editName.isEnabled = equipmentDetailsViewState.enableState
        binding.editHierarchyName.isEnabled = equipmentDetailsViewState.enableState
        binding.editCostCodeName.isEnabled = equipmentDetailsViewState.enableState
        binding.checkEcology.isEnabled = equipmentDetailsViewState.enableState
        binding.checkSafety.isEnabled = equipmentDetailsViewState.enableState
    }
}