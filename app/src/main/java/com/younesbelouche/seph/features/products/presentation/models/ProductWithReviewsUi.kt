package com.younesbelouche.seph.features.products.presentation.models

data class ProductWithReviewsUi(
    val product: ProductUi,
    val reviews: List<ReviewUi>,
    val areReviewsVisible: Boolean
)