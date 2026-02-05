package com.younesbelouche.seph.features.products.domain.repositories

import com.younesbelouche.seph.core.util.Result
import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.entities.ProductReviews
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<Result<List<Product>>>
    fun getProductReviews(): Flow<Result<List<ProductReviews>>>
}