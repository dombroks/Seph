package com.younesbelouche.seph.features.products.presentation.models

data class ProductReviewsUi(
    val productId: Long,
    val isHidden: Boolean,
    val reviews: List<ReviewUi>
)