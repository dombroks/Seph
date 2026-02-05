package com.younesbelouche.seph.features.products.data.mappers

import com.younesbelouche.seph.features.products.data.dto.review.ProductReviewsDto
import com.younesbelouche.seph.features.products.domain.entities.ProductReviews
import com.younesbelouche.seph.features.products.domain.entities.Review

object ProductReviewsMapper {

    fun fromDto(dto: ProductReviewsDto): ProductReviews =
        ProductReviews(
            productId = dto.productId,
            isHidden = dto.hide ?: false,
            reviews = dto.reviews.map { reviewDto ->
                Review(
                    authorName = reviewDto.name,
                    text = reviewDto.text,
                    rating = reviewDto.rating
                )
            }
        )

    fun fromDtoList(dtos: List<ProductReviewsDto>): List<ProductReviews> =
        dtos.map { fromDto(it) }
}
