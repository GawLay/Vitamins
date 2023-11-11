package com.codigo.vitamins.ui.diets

import androidx.recyclerview.widget.DiffUtil
import com.codigo.vitamins.data.model.DietsData

object DietDiffUtil : DiffUtil.ItemCallback<DietsData>() {
    override fun areItemsTheSame(oldItem: DietsData, newItem: DietsData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DietsData, newItem: DietsData): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name && oldItem.toolTip == newItem.toolTip &&
                oldItem.isSelected == newItem.isSelected
    }
}