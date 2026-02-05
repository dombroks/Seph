package com.younesbelouche.seph.features.products.data.mappers

import com.younesbelouche.seph.features.products.data.dto.product.ProductDto
import com.younesbelouche.seph.features.products.domain.entities.Product

object ProductMapper {

    fun fromDto(dto: ProductDto): Product =
        Product(
            id = dto.productId,
            name = dto.productName,
            description = dto.description,
            price = dto.price,
            imageSmallUrl = dto.imagesUrl.small,
            imageLargeUrl = dto.imagesUrl.large,
            brandId = dto.brand.id,
            brandName = dto.brand.name,
            isProductSet = dto.isProductSet,
            isSpecialBrand = dto.isSpecialBrand
        )

    fun fromDtoList(dtos: List<ProductDto>): List<Product> =
        dtos.map { fromDto(it) }
}
