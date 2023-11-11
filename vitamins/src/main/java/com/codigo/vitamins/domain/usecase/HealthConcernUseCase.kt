package com.codigo.vitamins.domain.usecase

import com.codigo.vitamins.data.model.HealthConcern
import com.codigo.vitamins.domain.repo.VitaminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HealthConcernUseCase {
    suspend fun getNutrientsList(fileName: String): Flow<HealthConcern?>
}

class HealthConcernUseCaseImpl @Inject constructor(
    private val vitaminRepository: VitaminRepository
) : HealthConcernUseCase {
    override suspend fun getNutrientsList(fileName: String) =
        vitaminRepository.getNutrientsList(fileName)


}