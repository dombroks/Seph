package com.younesbelouche.seph.features.products.domain.mappers

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

    fun toUiList(products: List<Product>): List<ProductUi> =
        products.map { toUi(it) }

    fun toUi(reviews: ProductReviews): ProductReviewsUi =
        ProductReviewsUi(
            productId = reviews.productId,
            isHidden = reviews.isHidden,
            reviews = reviews.reviews
                .filter { it.text != null }     // remove empty reviews
                .map {
                    ReviewUi(
                        authorName = it.authorName,
                        text = it.text!!,
                        rating = it.rating?.let { r -> "%.1f ★".format(r) }
                    )
                }
        )

    fun toUiList(reviews: List<ProductReviews>): List<ProductReviewsUi> =
        reviews.map { toUi(it) }
}