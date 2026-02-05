package com.younesbelouche.seph.features.products.data.datasources

import com.younesbelouche.seph.features.products.data.ProductsApi
import com.younesbelouche.seph.features.products.data.dto.product.ProductDto
import com.younesbelouche.seph.features.products.data.dto.review.ProductReviewsDto

class ProductRemoteDataSource(
    private val productsApi: ProductsApi
) {
    suspend fun getProducts(): List<ProductDto> =
        productsApi.getProducts()


    suspend fun getProductReviews(): List<ProductReviewsDto> =
        productsApi.getProductReviews()


}