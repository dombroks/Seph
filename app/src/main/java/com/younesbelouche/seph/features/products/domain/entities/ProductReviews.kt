package com.younesbelouche.seph.features.products.domain.entities

data class ProductReviews(
    val productId: Long,
    val isHidden: Boolean,
    val reviews: List<Review>
)
