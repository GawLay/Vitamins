package com.codigo.vitamins.ui.healthConcerns

import androidx.recyclerview.widget.DiffUtil
import com.codigo.vitamins.data.model.HealthConcernData

object HealthConcernDiffUtil : DiffUtil.ItemCallback<HealthConcernData>() {
    override fun areItemsTheSame(oldItem: HealthConcernData, newItem: HealthConcernData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: HealthConcernData,
        newItem: HealthConcernData
    ): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name &&
                oldItem.isSelected == newItem.isSelected
    }

}