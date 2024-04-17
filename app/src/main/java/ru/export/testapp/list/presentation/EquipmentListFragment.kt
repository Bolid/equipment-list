package ru.export.testapp.list.presentation

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.export.testapp.R
import ru.export.testapp.core.BaseFragment
import ru.export.testapp.core.Event
import ru.export.testapp.core.observeEvents
import ru.export.testapp.core.view.viewBinding
import ru.export.testapp.databinding.EquipmentListFragmentBinding
import ru.export.testapp.filter_list.EquipmentFilterSheet
import ru.export.testapp.filter_list.OnEquipmentFilterSaveListener
import ru.export.testapp.filter_list.model.EquipmentFilterParam
import ru.export.testapp.list.di.equipmentListModule
import ru.export.testapp.list.presentation.model.EquipmentEvent
import ru.export.testapp.list.presentation.model.EquipmentViewItem

class EquipmentListFragment : BaseFragment(R.layout.equipment_list_fragment), OnEquipmentFilterSaveListener {

    private val equipmentListViewModel by viewModel<EquipmentListViewModel>()
    private val binding by viewBinding(EquipmentListFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(equipmentListModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equipmentListViewModel.equipmentLiveData.observe(viewLifecycleOwner, ::onEquipmentList)
        binding.btnFilter.setOnClickListener {
            equipmentListViewModel.doFilter()
        }
        equipmentListViewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(equipmentListModule)
    }

    override fun onEquipmentFilterSave(params: EquipmentFilterParam) {
        equipmentListViewModel.onFilter(params)
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is EquipmentEvent.EquipmentFilterEvent -> onEquipmentFilter(event.filterParams)
            is EquipmentEvent.EquipmentFilteredElementEvent -> onEquipmentFilteredElement(event.list)
            else -> super.handleEvent(event)
        }
    }

    private fun onEquipmentFilteredElement(list: List<EquipmentViewItem>) {
        (binding.equipmentList.adapter as EquipmentListAdapter).update(list)
    }

    private fun onEquipmentFilter(filterParams: EquipmentFilterParam) {
        EquipmentFilterSheet()
            .apply { arguments = EquipmentFilterSheet.getBundle(filterParams) }
            .show(childFragmentManager, "filter")
    }

    private fun onEquipmentList(equipmentViewItems: List<EquipmentViewItem>) {
        binding.equipmentList.layoutManager = LinearLayoutManager(requireContext())
        binding.equipmentList.adapter = EquipmentListAdapter(equipmentViewItems)
        binding.equipmentList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
    }

}