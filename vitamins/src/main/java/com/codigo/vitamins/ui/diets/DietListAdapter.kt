package com.codigo.vitamins.ui.diets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.shared.extension.showToast
import com.codigo.vitamins.data.model.DietsData
import com.codigo.welcome.databinding.ItemDietBinding

class DietListAdapter(private val onDietSelect: (DietsData) -> Unit) :
    ListAdapter<DietsData, DietListAdapter.DietListVH>(DietDiffUtil) {
    private var isDisableAllCb: Boolean = false

    inner class DietListVH(private val binding: ItemDietBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DietsData) {
            binding.apply {
                cbDiet.isEnabled = !isDisableAllCb
                val context = root.context
                binding.tvDietName.text = item.name
                binding.ivHelp.setSafeOnClickListener {
                    context.showToast(item.toolTip)
                }
                binding.root.setSafeOnClickListener {
                    item.isSelected = !item.isSelected
                    binding.cbDiet.isChecked = item.isSelected
                    onDietSelect.invoke(item)
                    notifyItemChanged(bindingAdapterPosition)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietListVH {
        val binding = ItemDietBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DietListVH(binding)
    }

    override fun onBindViewHolder(holder: DietListVH, position: Int) {
        holder.bind(getItem(position))
    }


    @SuppressLint("NotifyDataSetChanged")
    fun toggleCheckBox(selected: Boolean) {
        isDisableAllCb = selected
        notifyDataSetChanged()
    }
}