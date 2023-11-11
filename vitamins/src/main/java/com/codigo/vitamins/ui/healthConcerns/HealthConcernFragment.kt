package com.codigo.vitamins.ui.healthConcerns

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.base.view.BaseFragment
import com.codigo.base.view.eventDispatcher.EventDispatcher
import com.codigo.base.view.eventDispatcher.MainEvent
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.shared.extension.showToast
import com.codigo.shared.utility.ItemTouchHelperCallback
import com.codigo.vitamins.data.model.DataToPrint
import com.codigo.vitamins.data.model.HealthConcernData
import com.codigo.vitamins.util.JSonFileName
import com.codigo.welcome.databinding.FragmentNutrientsBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HealthConcernFragment : BaseFragment<FragmentNutrientsBinding>() {
    private val viewModel by viewModels<HealthConcernViewModel>()
    private var healthConcernAdapter: HealthConcernAdapter? = null
    private var selectedHealthConcernList = arrayListOf<HealthConcernData>()
    private var prioritizeAdapter: PrioritizeAdapter? = null
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNutrientsBinding {
        return FragmentNutrientsBinding.inflate(inflater, container, false)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            EventDispatcher.dispatchEvent(MainEvent.UpdateProgressValue(25))
            EventDispatcher.dispatchEvent(MainEvent.ToggleIndicator(true))
        }
    }

    override fun onClickEvents() {
        binding.apply {
            includeBtnGrp.btnBack.setSafeOnClickListener {
                findNavController().popBackStack()
            }
            includeBtnGrp.btnNext.setSafeOnClickListener {
                //pass that list
                if (prioritizeAdapter?.currentList.isNullOrEmpty()) {
                    requireContext().showToast("Please Choose at least one!")
                    return@setSafeOnClickListener
                }

                val dataToPoint = DataToPrint(
                    healthConcernData = prioritizeAdapter?.currentList?.toList()
                )
                findNavController().navigate(
                    HealthConcernFragmentDirections.actionHealthConcernFragmentToDietFragment(
                        dataToPoint
                    )
                )
            }
        }
    }

    override fun initViews() {
        healthConcernAdapter = HealthConcernAdapter(::onChipSelected, ::onChipRemoved)
        prioritizeAdapter = PrioritizeAdapter()

        binding.apply {
            FlexboxLayoutManager(requireContext()).let {
                it.flexDirection = FlexDirection.ROW
                it.justifyContent = JustifyContent.FLEX_START
                rcHealthConcerns.layoutManager = it
                rcHealthConcerns.adapter = healthConcernAdapter
            }
            LinearLayoutManager(requireContext()).let {
                rcPrioritize.layoutManager = it
                rcPrioritize.adapter = prioritizeAdapter
                val itemTouchHelperCallback = ItemTouchHelperCallback { from, to ->
                    prioritizeAdapter?.dragItem(from, to)
                }
                val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
                itemTouchHelper.attachToRecyclerView(rcPrioritize)
            }
        }
    }

    private fun onChipSelected(healthConcernData: HealthConcernData) {
        selectedHealthConcernList.add(healthConcernData)
        prioritizeAdapter?.addItem(healthConcernData)
    }

    private fun onChipRemoved(healthConcernData: HealthConcernData) {
        selectedHealthConcernList.remove(healthConcernData)
        prioritizeAdapter?.removeItem(healthConcernData)
    }

    override fun observe() {
        MainScope().launch {
            viewModel.getHealthConcernList(JSonFileName.NUTRIENTS_JSON.fileName).collectLatest {
                healthConcernAdapter?.submitList(it?.list)
            }
        }
    }

}