package com.younesbelouche.seph.features.products.presentation.mappers

import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.entities.ProductReviews
import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi

object UiMapper {
    fun toUi(product: Product): ProductUi =
        ProductUi(
            id = product.id,
            name = product.name,
            description = product.description,
            price = "%.2f MAD".format(product.price),
            imageSmallUrl = product.imageSmallUrl,
            imageLargeUrl = product.imageLargeUrl,
            brandName = product.brandName,
            isProductSet = product.isProductSet,
            isSpecialBrand = product.isSpecialBrand
        )

    fun toProductUiList(products: List<Product>): List<ProductUi> =
        products.map { toUi(it) }

    fun toUi(
        product: Product,
        reviews: ProductReviews
    ): ProductReviewsUi =
        ProductReviewsUi(
            product = toUi(product),
            reviews = reviews.reviews
                .filter { !it.text.isNullOrBlank() }
                .map {
                    ReviewUi(
                        authorName = it.authorName,
                        text = it.text!!,
                        rating = it.rating.toString()
                    )
                },
            areReviewsVisible = false
        )
}