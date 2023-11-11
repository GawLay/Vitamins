package com.codigo.vitamins.ui.welcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codigo.base.view.BaseFragment
import com.codigo.base.view.eventDispatcher.EventDispatcher
import com.codigo.base.view.eventDispatcher.MainEvent
import com.codigo.shared.extension.setSafeOnClickListener
import com.codigo.welcome.R
import com.codigo.welcome.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWelcomeBinding.inflate(inflater, container, false)

    override fun onClickEvents() {
        binding.btnStart.setSafeOnClickListener {
            findNavController().navigate(
                R.id.action_welcomeFragment_to_healthConcernFragment
            )
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            EventDispatcher.dispatchEvent(MainEvent.UpdateProgressValue(0))
            EventDispatcher.dispatchEvent(MainEvent.ToggleIndicator(false))
        }
    }
}