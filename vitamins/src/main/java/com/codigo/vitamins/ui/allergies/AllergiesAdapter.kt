package com.codigo.vitamins.ui.allergies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.vitamins.data.model.AllergiesData
import com.codigo.welcome.databinding.ItemAllergiesRowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllergiesAdapter(private val onAllergiesChoose: (AllergiesData) -> Unit) :
    ListAdapter<AllergiesData, AllergiesAdapter.AllergiesVH>(AllergiesDiffUtil) {
    var originalList: List<AllergiesData>? = emptyList()

    inner class AllergiesVH(private val binding: ItemAllergiesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AllergiesData) {
            binding.tvListItem.text = item.name
            binding.root.setSafeOnClickListener {
                onAllergiesChoose.invoke(item)
            }
        }

    }

    private fun submitFilteredList(filteredList: List<AllergiesData>?) {
        submitList(filteredList)
    }

    fun filter(
        query: String,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch(Dispatchers.Default) {
            delay(300)
            val filteredList = originalList?.filter { it.name.contains(query, ignoreCase = true) }
            withContext(Dispatchers.Main){
                submitFilteredList(filteredList)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergiesVH {
        val binding =
            ItemAllergiesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllergiesVH(binding)
    }

    override fun onBindViewHolder(holder: AllergiesVH, position: Int) {
        holder.bind(getItem(position))
    }
}