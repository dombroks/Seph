package com.younesbelouche.seph.features.products.presentation.mappers

import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.entities.ProductReviews
import com.younesbelouche.seph.features.products.presentation.models.ProductWithReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi

object UiMapper {
    fun toProductUi(product: Product): ProductUi =
        ProductUi(
            id = product.id,
            name = product.name,
            description = product.description,
            price = "%.2f €".format(product.price),
            imageSmallUrl = product.imageSmallUrl,
            imageLargeUrl = product.imageLargeUrl,
            brandName = product.brandName,
            isProductSet = product.isProductSet,
            isSpecialBrand = product.isSpecialBrand
        )


    fun toProductWithReviewsUi(
        product: Product,
        reviews: ProductReviews
    ): ProductWithReviewsUi =
        ProductWithReviewsUi(
            product = toProductUi(product),
            reviews = reviews.reviews
                .map {
                    ReviewUi(
                        authorName = it.authorName ?: "Unknown",
                        text = it.text ?: " ",
                        rating = it.rating.toString()
                    )
                },
            areReviewsVisible = false
        )
}