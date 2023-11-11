package com.codigo.vitamins.ui.allergies

import androidx.recyclerview.widget.DiffUtil
import com.codigo.vitamins.data.model.AllergiesData

object AllergiesDiffUtil : DiffUtil.ItemCallback<AllergiesData>() {
    override fun areItemsTheSame(oldItem: AllergiesData, newItem: AllergiesData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AllergiesData, newItem: AllergiesData): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}