package com.codigo.vitamins.ui.healthConcerns

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.shared.extension.showToast
import com.codigo.vitamins.data.model.HealthConcernData
import com.codigo.welcome.databinding.ItemHealthConcernBinding
import com.codigo.shared.R as SharedR

class HealthConcernAdapter(
    private val onChipSelected: (HealthConcernData) -> Unit,
    private val onChipRemoved: (HealthConcernData) -> Unit
) :
    ListAdapter<HealthConcernData, HealthConcernAdapter.HealthConcernVH>(HealthConcernDiffUtil) {
    private val selectedItems = mutableListOf<HealthConcernData>()
    private val maxCount = 5

    inner class HealthConcernVH(private val binding: ItemHealthConcernBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HealthConcernData) {
            updateColor(item)
            binding.apply {
                root.setSafeOnClickListener {
                    selectAndValidateChip(item, bindingAdapterPosition)
                }
                tvChip.text = item.name
            }
        }

        private fun selectAndValidateChip(item: HealthConcernData, bindingAdapterPosition: Int) {
            if (item.isSelected) {
                selectedItems.remove(item)
                item.isSelected = false
                onChipRemoved.invoke(item)
            } else {
                if (selectedItems.size < maxCount) {
                    selectedItems.add(item)
                    onChipSelected.invoke(item)
                    item.isSelected = true
                } else {
                    binding.root.context.showToast("Max Selection has reached!")
                }
            }
            notifyItemChanged(bindingAdapterPosition)

        }

        private fun updateColor(item: HealthConcernData) {
            binding.apply {
                val context = root.context
                val selectedBackground =
                    ContextCompat.getDrawable(context, SharedR.drawable.chip_item_selected)
                val normalBackground =
                    ContextCompat.getDrawable(context, SharedR.drawable.chip_item_normal)
                if (item.isSelected) {
                    tvChip.background = selectedBackground
                } else {
                    tvChip.background = normalBackground
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthConcernVH {
        val binding =
            ItemHealthConcernBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthConcernVH(binding)
    }

    override fun onBindViewHolder(holder: HealthConcernVH, position: Int) {
        holder.bind(getItem(position))
    }
}

