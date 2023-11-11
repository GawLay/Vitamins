package com.codigo.vitamins.di

import com.codigo.vitamins.data.VitaminRepositoryImpl
import com.codigo.vitamins.domain.repo.VitaminRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VitaminRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideVitaminRepository(vitaminRepositoryImpl: VitaminRepositoryImpl): VitaminRepository
}