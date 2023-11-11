package com.codigo.vitamins.ui.diets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigo.welcome.databinding.ItemDietBinding

class DietHeaderAdapter(private val onNoneCheck: (Boolean) -> Unit) :
    RecyclerView.Adapter<DietHeaderAdapter.DietHeaderVH>() {
    inner class DietHeaderVH(private val binding: ItemDietBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvDietName.text = "None"
            binding.cbDiet.setOnCheckedChangeListener { _, b ->
                onNoneCheck.invoke(b)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietHeaderVH {
        val binding = ItemDietBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DietHeaderVH(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: DietHeaderVH, position: Int) {
        holder.bind()
    }
}