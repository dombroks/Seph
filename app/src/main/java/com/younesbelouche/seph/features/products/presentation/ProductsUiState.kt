package com.younesbelouche.seph.features.products.presentation

import com.younesbelouche.seph.features.products.presentation.models.ProductWithReviewsUi

data class ProductsUiState(
    val productsWithReviews: List<ProductWithReviewsUi> = emptyList(),
    val searchInput: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val reviewsSortOption: ReviewsSortOption = ReviewsSortOption.Best2Worst
)

enum class ReviewsSortOption {
    Best2Worst,
    Worst2Best
}
