package com.codigo.vitamins.ui.personalized

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.codigo.base.view.BaseFragment
import com.codigo.base.view.eventDispatcher.EventDispatcher
import com.codigo.base.view.eventDispatcher.MainEvent
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.shared.extension.showLog
import com.codigo.shared.extension.showToast
import com.codigo.vitamins.data.model.DataToPrint
import com.codigo.welcome.R
import com.codigo.welcome.databinding.FragmentPersonalizeVitaminBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonalizeFragment : BaseFragment<FragmentPersonalizeVitaminBinding>() {
    private val navArgs by navArgs<PersonalizeFragmentArgs>()
    private lateinit var dataToPrintArgs: DataToPrint

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonalizeVitaminBinding {
        return FragmentPersonalizeVitaminBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        setupArgs()
        listeners()
    }

    override fun onClickEvents() {
        binding.apply {
            btnPersonalized.setSafeOnClickListener {
                validateAndPrint()
            }
        }
    }

    private fun validateAndPrint() {
        showLog("test ${dataToPrintArgs.toString()}")
        if (binding.rgAlcoholic.isSelectedBtn() && binding.rgSmoke.isSelectedBtn() && binding.rgDailyExposure.isSelectedBtn()) {
            val json = Gson().toJson(dataToPrintArgs)
            print("Your Dialy Vitamins is")
            println("=====================")
            println(json)

            lifecycleScope.launch {
                EventDispatcher.dispatchEvent(MainEvent.UpdateProgressValue(100))
            }
        } else {
            requireContext().showToast("Please choose all the options to print")
        }
    }

    private fun RadioGroup.isSelectedBtn(): Boolean {
        return checkedRadioButtonId != -1
    }

    private fun listeners() {
        binding.apply {
            rgDailyExposure.let {
                it.setOnCheckedChangeListener { radioGroup, id ->
                    when (id) {
                        R.id.rb_exposure_yes -> {
                            dataToPrintArgs.isDailyExposure = true
                        }

                        R.id.rb_exposure_no -> {
                            dataToPrintArgs.isDailyExposure = false
                        }
                    }
                }
            }

            rgSmoke.let {
                it.setOnCheckedChangeListener { radioGroup, id ->
                    when (id) {
                        R.id.rb_smoke_yes -> {
                            dataToPrintArgs.isSmoke = true
                        }

                        R.id.rb_smoke_no -> {
                            dataToPrintArgs.isSmoke = false
                        }
                    }
                }
            }
            rgAlcoholic.let {
                it.setOnCheckedChangeListener { _, id ->
                    when (id) {
                        R.id.rb_alcoholic_first -> {
                            dataToPrintArgs.alcohol =
                                getString(R.string.vitamins_alcohol_first_level)
                        }

                        R.id.rb_alcoholic_second -> {
                            dataToPrintArgs.alcohol =
                                getString(R.string.vitamins_alcohol_second_level)
                        }

                        R.id.rb_alcoholic_third -> {
                            dataToPrintArgs.alcohol =
                                getString(R.string.vitamins_alcohol_third_level)
                        }
                    }
                }
            }
        }
    }

    private fun setupArgs() {
        dataToPrintArgs = navArgs.dataToPrint
    }

}