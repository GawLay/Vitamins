package com.codigo.vitamins.ui.healthConcerns

import androidx.lifecycle.ViewModel
import com.codigo.vitamins.domain.usecase.HealthConcernUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HealthConcernViewModel @Inject constructor(
    private val healthConcernUseCase: HealthConcernUseCase
) : ViewModel() {
    suspend fun getHealthConcernList(fileName: String) =
        healthConcernUseCase.getNutrientsList(fileName)


}