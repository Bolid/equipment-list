package ru.export.testapp.filter_list

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.export.testapp.R
import ru.export.testapp.core.utils.ext.findFromParent
import ru.export.testapp.core.view.viewBinding
import ru.export.testapp.databinding.DialogEquipmentListFilterBinding
import ru.export.testapp.filter_list.model.EquipmentFilterParam

class EquipmentFilterSheet : BottomSheetDialogFragment(R.layout.dialog_equipment_list_filter) {

    private val binding by viewBinding(DialogEquipmentListFilterBinding::bind)
    private val onEquipmentFilterSaveListener: OnEquipmentFilterSaveListener by findFromParent()
    private val equipmentParams by lazy {
        arguments?.getParcelable<EquipmentFilterParam>(ARG_EQUIPMENT_FILTER_PARAMS) ?: throw NullPointerException()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilter(equipmentParams)
        binding.save.setOnClickListener {
            onEquipmentFilterSaveListener.onEquipmentFilterSave(equipmentParams)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext()).apply {
            setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val parentLayout =
                    bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                parentLayout?.let { view ->
                    val behaviour = BottomSheetBehavior.from(view)
                    setupFullHeight(view)
                    behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun setFilter(equipmentFilterParam: EquipmentFilterParam) {
        binding.filterList.adapter = EquipmentFilterAdapter(equipmentFilterParam.criticalityList, ::onClick)
        binding.filterList.layoutManager = LinearLayoutManager(requireContext())
        binding.filterList.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
    }

    private fun onClick(position: Int) {
        val value = equipmentParams.criticalityList[position].checked
        equipmentParams.criticalityList[position].checked = !value
        binding.filterList.adapter?.notifyItemChanged(position)
        binding.save.isEnabled = equipmentParams.criticalityList.any { it.checked }
    }

    companion object {
        private const val ARG_EQUIPMENT_FILTER_PARAMS = "arg_equipment_filter_params"

        fun getBundle(filterParams: EquipmentFilterParam): Bundle {
            return Bundle().apply { putParcelable(ARG_EQUIPMENT_FILTER_PARAMS, filterParams) }
        }
    }

}

interface OnEquipmentFilterSaveListener {
    fun onEquipmentFilterSave(params: EquipmentFilterParam)
}