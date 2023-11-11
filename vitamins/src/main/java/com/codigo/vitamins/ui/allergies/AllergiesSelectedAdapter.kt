package com.codigo.vitamins.ui.allergies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.shared.extension.visibleExt
import com.codigo.vitamins.data.model.AllergiesData
import com.codigo.welcome.databinding.ItemHealthConcernBinding

class AllergiesSelectedAdapter() :
    ListAdapter<AllergiesData, AllergiesSelectedAdapter.AllergiesSelectedVH>(AllergiesDiffUtil) {
    inner class AllergiesSelectedVH(private val binding: ItemHealthConcernBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AllergiesData) {
            binding.apply {
                tvChip.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergiesSelectedVH {
        val binding =
            ItemHealthConcernBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllergiesSelectedVH(binding)
    }

    override fun onBindViewHolder(holder: AllergiesSelectedVH, position: Int) {
        holder.bind(getItem(position))
    }
}