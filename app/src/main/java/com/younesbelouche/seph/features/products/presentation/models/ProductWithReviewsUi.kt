package com.younesbelouche.seph.features.products.presentation.models

import com.younesbelouche.seph.features.products.presentation.ReviewsSortOption

data class ProductWithReviewsUi(
    val product: ProductUi,
    val reviews: List<ReviewUi>,
    val areReviewsVisible: Boolean,
    val reviewsSortOption: ReviewsSortOption = ReviewsSortOption.Best2Worst
)