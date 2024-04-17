package ru.export.testapp.flow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.export.testapp.R
import ru.export.testapp.details.presentation.EquipmentDetailsFragment
import ru.export.testapp.flow.di.equipmentFlowModule
import ru.export.testapp.list.presentation.EquipmentListFragment

class EquipmentFlowFragment : Fragment(R.layout.equipment_flow_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(equipmentFlowModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(equipmentFlowModule)
    }

    private fun init() {
        setEquipmentList()
        setEquipmentDetails()
    }

    private fun setEquipmentDetails() {
        childFragmentManager
            .beginTransaction()
            .add(R.id.equipment_details, EquipmentDetailsFragment())
            .commitNow()
    }

    private fun setEquipmentList() {
        childFragmentManager
            .beginTransaction()
            .add(R.id.equipment_list, EquipmentListFragment())
            .commitNow()
    }

}