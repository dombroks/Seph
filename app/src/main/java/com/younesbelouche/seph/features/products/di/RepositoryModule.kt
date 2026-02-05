package com.younesbelouche.seph.features.products.di

import com.younesbelouche.seph.features.products.data.ProductsApi
import com.younesbelouche.seph.features.products.data.datasources.ProductRemoteDataSource
import com.younesbelouche.seph.features.products.data.repositories.ProductsRepositoryImpl
import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProductsRemoteDataSource(
        productsApi: ProductsApi,
    ): ProductRemoteDataSource {
        return ProductRemoteDataSource(
            productsApi = productsApi,
        )
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsRemoteDataSource: ProductRemoteDataSource,
    ): ProductsRepository {
        return ProductsRepositoryImpl(
            productsRemoteDataSource = productsRemoteDataSource,
        )
    }
}