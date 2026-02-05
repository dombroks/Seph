package com.younesbelouche.seph.features.products.data.dto.review

import com.google.gson.annotations.SerializedName

data class ProductReviewsDto(
    @SerializedName("product_id")
    val productId: Long,

    @SerializedName("hide")
    val hide: Boolean? = null,

    @SerializedName("reviews")
    val reviews: List<ReviewDto>
)
