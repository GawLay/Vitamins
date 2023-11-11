package com.codigo.vitamins.ui.healthConcerns

import android.provider.SyncStateContract.Helpers
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.vitamins.data.model.HealthConcernData
import com.codigo.welcome.databinding.ItemPrioritizeBinding

class PrioritizeAdapter :
    ListAdapter<HealthConcernData, PrioritizeAdapter.PrioritizeVH>(HealthConcernDiffUtil) {
    inner class PrioritizeVH(private val binding: ItemPrioritizeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HealthConcernData) {
            binding.tvChip.text = item.name
            item.position =bindingAdapterPosition
        }
    }
    fun dragItem(fromPosition: Int, toPosition: Int) {
        submitList(currentList.toMutableList().apply {
            val item = removeAt(fromPosition)
            add(toPosition, item)
            updateItemPositions()
        })
    }

    fun addItem(item:HealthConcernData){
        submitList(currentList.toMutableList().apply { add(item) })
    }
    fun removeItem(item:HealthConcernData){
        submitList(currentList.toMutableList().apply { remove(item) })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrioritizeVH {
        val binding =
            ItemPrioritizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrioritizeVH(binding)
    }

    override fun onBindViewHolder(holder: PrioritizeVH, position: Int) {
        holder.bind(getItem(position))
    }

    private fun updateItemPositions() {
        for ((index, item) in currentList.withIndex()) {
            item.position = index
        }
    }
}

