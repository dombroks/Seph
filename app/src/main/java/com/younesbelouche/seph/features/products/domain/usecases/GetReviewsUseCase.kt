package com.younesbelouche.seph.features.products.domain.usecases

import com.younesbelouche.seph.core.util.Result
import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.entities.ProductReviews
import com.younesbelouche.seph.features.products.domain.entities.Review
import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetReviewsUseCase(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<Result<List<ProductReviews>>> {
        return productsRepository.getProductReviews()
    }
}