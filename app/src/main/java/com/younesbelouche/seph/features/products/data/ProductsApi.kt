package com.younesbelouche.seph.features.products.data

import com.younesbelouche.seph.features.products.data.dto.product.ProductDto
import com.younesbelouche.seph.features.products.data.dto.review.ProductReviewsDto
import retrofit2.http.GET

interface ProductsApi {

    @GET("items.json")
    suspend fun getProducts(): List<ProductDto>

    @GET("reviews.json")
    suspend fun getProductReviews(): List<ProductReviewsDto>
}