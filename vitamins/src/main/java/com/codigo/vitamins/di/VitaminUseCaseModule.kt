package com.codigo.vitamins.di

import com.codigo.vitamins.domain.usecase.AllergiesUseCase
import com.codigo.vitamins.domain.usecase.AllergiesUseCaseImpl
import com.codigo.vitamins.domain.usecase.DietUseCase
import com.codigo.vitamins.domain.usecase.DietUseCaseImpl
import com.codigo.vitamins.domain.usecase.HealthConcernUseCase
import com.codigo.vitamins.domain.usecase.HealthConcernUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class VitaminUseCaseModule {
    @Binds
    abstract fun provideHealthConcernUseCase(
        getHealthConcernUseCaseImpl: HealthConcernUseCaseImpl
    ): HealthConcernUseCase

    @Binds
    abstract fun provideDietUseCase(
        getDietUseCaseImpl: DietUseCaseImpl
    ): DietUseCase

    @Binds
    abstract fun provideAllergiesUseCase(
        getAllergiseUsecaseImpl: AllergiesUseCaseImpl
    ): AllergiesUseCase
}