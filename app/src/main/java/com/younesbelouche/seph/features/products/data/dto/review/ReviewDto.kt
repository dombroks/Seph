package com.younesbelouche.seph.features.products.data.dto.review

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("text")
    val text: String? = null,

    @SerializedName("rating")
    val rating: Double? = null
)
