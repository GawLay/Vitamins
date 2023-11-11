package com.codigo.vitamins.ui.diets

import androidx.lifecycle.ViewModel
import com.codigo.vitamins.domain.usecase.DietUseCase
import com.codigo.vitamins.domain.usecase.HealthConcernUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DietViewModel @Inject constructor(
    private val dietUseCase: DietUseCase
) : ViewModel() {
    suspend fun getDietList(fileName: String) =
        dietUseCase.getDietUseCase(fileName)
}