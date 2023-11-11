package com.codigo.vitamins.domain.usecase

import com.codigo.vitamins.data.model.Allergies
import com.codigo.vitamins.data.model.Diets
import com.codigo.vitamins.domain.repo.VitaminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AllergiesUseCase {
    suspend fun getAllergiesList(fileName: String): Flow<Allergies?>
}


class AllergiesUseCaseImpl @Inject constructor(
    private val vitaminRepository: VitaminRepository
) : AllergiesUseCase {
    override suspend fun getAllergiesList(fileName: String) =
        vitaminRepository.getAllergiesList(fileName)
}