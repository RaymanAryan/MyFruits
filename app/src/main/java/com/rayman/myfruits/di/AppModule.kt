package com.rayman.myfruits.di

import com.rayman.myfruits.data.network.FruitService
import com.rayman.myfruits.data.network.RetrofitInstance
import com.rayman.myfruits.repository.FruitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFruitService(): FruitService = RetrofitInstance.fruitService

    @Provides
    @Singleton
    fun provideFruitRepository(): FruitRepository = FruitRepository(RetrofitInstance.fruitService)
}