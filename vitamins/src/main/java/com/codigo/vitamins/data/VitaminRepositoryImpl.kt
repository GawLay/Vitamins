package com.codigo.vitamins.data

import android.content.Context
import com.codigo.shared.extension.readAssetFile
import com.codigo.shared.extension.showLog
import com.codigo.vitamins.data.model.Allergies
import com.codigo.vitamins.data.model.Diets
import com.codigo.vitamins.data.model.HealthConcern
import com.codigo.vitamins.domain.repo.VitaminRepository
import com.codigo.vitamins.util.ext.toObject
import com.codigo.vitamins.util.ext.transformHealthConcernFromJson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VitaminRepositoryImpl @Inject() constructor(
    @ApplicationContext val  appContext:Context
):VitaminRepository {
    override suspend fun getNutrientsList(fileName: String): Flow<HealthConcern?> {
        return flow {
            val json = appContext.assets.readAssetFile(fileName).trimIndent()
            emit(json.toObject())
        }
    }

    override suspend fun getDietsList(fileName: String): Flow<Diets?> {
        return flow {
            val json = appContext.assets.readAssetFile(fileName).trimIndent()
            emit(json.toObject())
        }
    }

    override suspend fun getAllergiesList(fileName: String): Flow<Allergies?> {
        return flow {
            val json = appContext.assets.readAssetFile(fileName).trimIndent()
            emit(json.toObject())
        }
    }

}