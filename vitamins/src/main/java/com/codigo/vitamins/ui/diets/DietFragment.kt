package com.codigo.vitamins.ui.diets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.base.view.BaseFragment
import com.codigo.base.view.eventDispatcher.EventDispatcher
import com.codigo.base.view.eventDispatcher.MainEvent
import com.codigo.vitamins.data.model.DataToPrint
import com.codigo.vitamins.data.model.DietsData
import com.codigo.vitamins.util.JSonFileName
import com.codigo.welcome.databinding.FragmentDietsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DietFragment : BaseFragment<FragmentDietsBinding>() {
    private var dietAdapter: DietListAdapter? = null
    private lateinit var parentAdapter: ConcatAdapter
    private val dietHeaderAdapter = DietHeaderAdapter(::onDietNoneCheck)
    private val dietDataList = arrayListOf<DietsData>()
    private val viewModel by viewModels<DietViewModel>()
    private val navArgs by navArgs<DietFragmentArgs>()
    private lateinit var dataToPrintArgs: DataToPrint
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDietsBinding {
        return FragmentDietsBinding.inflate(inflater, container, false)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            EventDispatcher.dispatchEvent(MainEvent.UpdateProgressValue(50))
        }
    }

    override fun onClickEvents() {
        binding.includeBtnGrp.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                //;aslkdjf
                dataToPrintArgs.dietsData = dietDataList
                findNavController().navigate(
                    DietFragmentDirections.actionDietFragmentToAllergiesFragment(
                        dataToPrintArgs
                    )
                )
            }
        }
    }

    override fun initViews() {
        super.initViews()
        setupArgs()
        dietAdapter = DietListAdapter(::onDietSelect)
        parentAdapter = ConcatAdapter(dietHeaderAdapter, dietAdapter)
        binding.rcDiets.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = parentAdapter
        }
    }

    private fun setupArgs() {
        dataToPrintArgs = navArgs.dataToPrint

    }

    private fun onDietSelect(dietData: DietsData) {
        if (dietData.isSelected) {
            dietDataList.add(dietData)
        } else {
            dietDataList.remove(dietData)
        }
    }

    private fun onDietNoneCheck(isSelected: Boolean) {
        dietAdapter?.toggleCheckBox(isSelected)
    }

    override fun observe() {
        lifecycleScope.launch {
            viewModel.getDietList(JSonFileName.DIETS_JSON.fileName).collectLatest {
                if (it != null) {
                    val dietsDataList = it.list
                    dietAdapter?.submitList(dietsDataList)
                }
            }
        }
    }
}