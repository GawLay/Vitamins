package com.codigo.vitamins.domain.repo

import com.codigo.vitamins.data.model.Allergies
import com.codigo.vitamins.data.model.Diets
import com.codigo.vitamins.data.model.HealthConcern
import kotlinx.coroutines.flow.Flow

interface VitaminRepository {
    suspend fun getNutrientsList(fileName: String): Flow<HealthConcern?>
    suspend fun getDietsList(fileName: String): Flow<Diets?>
    suspend fun getAllergiesList(fileName: String): Flow<Allergies?>
}