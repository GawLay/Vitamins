package com.codigo.vitamins.ui.allergies

import androidx.lifecycle.ViewModel
import com.codigo.vitamins.domain.usecase.AllergiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllergiesViewModel @Inject constructor(
    private val allergiesUseCase: AllergiesUseCase
) : ViewModel() {
    suspend fun getAllergiesList(fileName: String) = allergiesUseCase.getAllergiesList(fileName)
}