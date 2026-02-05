package com.younesbelouche.seph.features.products.presentation

import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi

data class ProductsUiState(
    val productsWithReviews: List<ProductReviewsUi> = emptyList(),
    val searchInput: String = "",
    val isLoading: Boolean = false
)
