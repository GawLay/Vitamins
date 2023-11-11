package com.codigo.vitamins.ui.allergies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.base.view.BaseFragment
import com.codigo.base.view.eventDispatcher.EventDispatcher
import com.codigo.base.view.eventDispatcher.MainEvent
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.vitamins.data.model.AllergiesData
import com.codigo.vitamins.data.model.DataToPrint
import com.codigo.vitamins.util.JSonFileName
import com.codigo.welcome.databinding.FragmentAllergiesBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@AndroidEntryPoint
class AllergiesFragment : BaseFragment<FragmentAllergiesBinding>() {
    private val viewModel by viewModels<AllergiesViewModel>()
    private val navArgs by navArgs<AllergiesFragmentArgs>()
    private lateinit var dataToPrintArgs: DataToPrint
    private lateinit var allergiesListAdapter: AllergiesAdapter
    private lateinit var allergiesSelectedListAdapter: AllergiesSelectedAdapter
    private var selectedAllergiesList = arrayListOf<AllergiesData>()
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllergiesBinding {
        return FragmentAllergiesBinding.inflate(inflater, container, false)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            EventDispatcher.dispatchEvent(MainEvent.UpdateProgressValue(75))
        }
    }

    override fun onClickEvents() {
        binding.includeBtnGrp.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setSafeOnClickListener {
                dataToPrintArgs.allergiesSelectedData = selectedAllergiesList
                findNavController().navigate(
                    AllergiesFragmentDirections.actionAllergiesFragmentToPersonalizeFragment(
                        dataToPrintArgs
                    )
                )
            }
        }
    }

    override fun initViews() {
        setupArgs()
        setupRc()
        listener()
    }

    private fun setupRc() {
        allergiesListAdapter = AllergiesAdapter(::onAlergiesChoose)
        allergiesSelectedListAdapter = AllergiesSelectedAdapter()
        binding.rcSearch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = allergiesListAdapter
        }
        binding.rcSelected.apply {
            FlexboxLayoutManager(requireContext()).let {
                it.flexDirection = FlexDirection.ROW
                it.justifyContent = JustifyContent.FLEX_START
                layoutManager = it
                adapter = allergiesSelectedListAdapter
            }
        }
    }

    private fun listener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        newText.let { allergiesListAdapter.filter(it, this) }
                    }
                } else {
                    allergiesListAdapter.submitList(allergiesListAdapter.originalList)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
    }

    private fun setupArgs() {
        dataToPrintArgs = navArgs.dataToPrint

    }

    override fun observe() {
        lifecycleScope.launch {
            viewModel.getAllergiesList(JSonFileName.ALLERGIES_JSON.fileName).collectLatest {
                allergiesListAdapter.submitList(it?.allergiesData)
                allergiesListAdapter.originalList = it?.allergiesData
            }
        }
    }

    private fun onAlergiesChoose(allergies: AllergiesData) {
        selectedAllergiesList.add(allergies)
        allergiesSelectedListAdapter.submitList(selectedAllergiesList)
    }

}