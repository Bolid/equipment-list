package ru.export.testapp.list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.export.testapp.R
import ru.export.testapp.list.presentation.model.EquipmentViewItem

class EquipmentListAdapter(private val list: List<EquipmentViewItem>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return EquipmentListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.equipment_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as EquipmentListViewHolder).bind(list[position])
    }

    fun update(newList: List<EquipmentViewItem>) {
        val count = list.size
        (list as MutableList).clear()
        notifyItemRangeRemoved(0, count)
        list.addAll(newList)
        notifyItemRangeInserted(0, newList.size)
    }

    inner class EquipmentListViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(item: EquipmentViewItem) {
            itemView.findViewById<TextView>(R.id.code).text = item.code
            itemView.findViewById<TextView>(R.id.name).text = item.name

            with(itemView.findViewById<TextView>(R.id.status)) {
                text = item.status
                setBackgroundColor(itemView.context.getColor(item.statusBackground))
            }

            with(itemView.findViewById<TextView>(R.id.criticality)) {
                text = item.criticality
                setBackgroundColor(itemView.context.getColor(item.criticalityBackground))
            }

            itemView.setOnClickListener { item.onClick.invoke(item.code) }
        }

    }
}