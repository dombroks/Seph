package com.younesbelouche.seph.features.products.presentation.models

data class ProductReviewsUi(
    val product: ProductUi,
    val reviews: List<ReviewUi>,
    val areReviewsVisible: Boolean
)