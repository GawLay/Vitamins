package com.codigo.healthcare

import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.codigo.base.view.BaseActivity
import com.codigo.base.view.eventDispatcher.EventDispatcher
import com.codigo.base.view.eventDispatcher.MainEvent
import com.codigo.healthcare.databinding.ActivityMainBinding
import com.codigo.shared.extension.invisibleExt
import com.codigo.shared.extension.visibleExt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun setBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)
    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun listener() {
        lifecycleScope.launch {
            EventDispatcher.dataFlow.collectLatest {
                Log.e("EVETN", "TOGGLE ${it}")
                when (it) {
                    is MainEvent.UpdateProgressValue -> {
                        Log.e("EVETN", "value ${it.progressValue}")
                        binding.progressIndicator.setProgressCompat(it.progressValue, true)
                    }

                    is MainEvent.ToggleIndicator -> {
                        Log.e("EVETN", "TOGGLE ${it.flag}")
                        if (it.flag) {
                            binding.progressIndicator.visibleExt()
                        } else {
                            binding.progressIndicator.invisibleExt()
                        }
                    }
                }
            }
        }
    }
}
