package com.codigo.vitamins.domain.usecase

import com.codigo.vitamins.data.model.Diets
import com.codigo.vitamins.domain.repo.VitaminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DietUseCase {
    suspend fun getDietUseCase(fileName: String): Flow<Diets?>
}

class DietUseCaseImpl @Inject constructor(
    private val vitaminRepository: VitaminRepository
) : DietUseCase {
    override suspend fun getDietUseCase(fileName: String) =
        vitaminRepository.getDietsList(fileName)
}