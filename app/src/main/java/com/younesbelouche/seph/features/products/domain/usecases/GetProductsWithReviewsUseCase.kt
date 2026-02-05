package com.younesbelouche.seph.features.products.domain.usecases

import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository

class GetProductsWithReviewsUseCase (
    private val productsRepository: ProductsRepository
) {
}