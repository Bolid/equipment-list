package ru.export.testapp.filter_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.export.testapp.R
import ru.export.testapp.filter_list.model.EquipmentFilterParam

class EquipmentFilterAdapter(
    val list: List<EquipmentFilterParam.CriticalityParam>,
    val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return EquipmentFilterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.equipment_filter_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as EquipmentFilterViewHolder).bind(list[position])
    }

    inner class EquipmentFilterViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(item: EquipmentFilterParam.CriticalityParam) {
            itemView.findViewById<TextView>(R.id.value).text = item.name
            itemView.findViewById<CheckBox>(R.id.check).isChecked = item.checked
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }

    }
}