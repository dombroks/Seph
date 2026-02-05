package com.younesbelouche.seph.features.products.data

import com.younesbelouche.seph.features.products.data.dto.product.ProductDto
import com.younesbelouche.seph.features.products.data.dto.review.ProductReviewsDto
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("products/reviews")
    suspend fun getProductReviews(): List<ProductReviewsDto>
}