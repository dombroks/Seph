package com.younesbelouche.seph.features.products.di


import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository
import com.younesbelouche.seph.features.products.domain.usecases.GetProductsUseCase
import com.younesbelouche.seph.features.products.domain.usecases.GetReviewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetReviewsUseCase(productsRepository: ProductsRepository): GetReviewsUseCase {
        return GetReviewsUseCase(productsRepository)
    }
}