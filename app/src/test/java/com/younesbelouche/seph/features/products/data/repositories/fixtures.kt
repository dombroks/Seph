package com.younesbelouche.seph.features.products.data.repositories

import com.younesbelouche.seph.features.products.data.dto.product.BrandDto
import com.younesbelouche.seph.features.products.data.dto.product.ImagesUrlDto
import com.younesbelouche.seph.features.products.data.dto.product.ProductDto

val dummyProducts = listOf(
    ProductDto(
        productId = 1L,
        productName = "Lipstick",
        description = "Red matte lipstick",
        price = 19.99,
        imagesUrl = ImagesUrlDto(small = "https://example.com/mascara.jpg", large = ""),
        brand = BrandDto("B2", name = "Brand2"),
        isProductSet = false,
        isSpecialBrand = false
    ),
    ProductDto(
        productId = 2L,
        productName = "Mascara",
        description = "Waterproof mascara",
        price = 15.49,
        imagesUrl = ImagesUrlDto(small = "https://example.com/mascara.jpg", large = ""),
        brand = BrandDto("B1", name = "Brand1"),
        isProductSet = false,
        isSpecialBrand = true
    )
)