package com.younesbelouche.seph.features.products.presentation

import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi

data class ProductsUiState(
    val products: List<ProductUi> = emptyList(),
    val reviews: List<ProductReviewsUi> = emptyList(),
    val searchInput: String = "",
    val isLoading: Boolean = false
)
