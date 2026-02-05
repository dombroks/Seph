package com.younesbelouche.seph.features.products.domain.usecases

import com.younesbelouche.seph.core.util.Result
import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<Result<List<Product>>> {
        return productsRepository.getProducts()
    }
}