package com.younesbelouche.seph.features.products.data.dto.product

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("product_id")
    val productId: Long,

    @SerializedName("product_name")
    val productName: String,

    val description: String,
    val price: Double,

    @SerializedName("images_url")
    val imagesUrl: ImagesUrlDto,

    @SerializedName("c_brand")
    val brand: BrandDto,

    @SerializedName("is_productSet")
    val isProductSet: Boolean,

    @SerializedName("is_special_brand")
    val isSpecialBrand: Boolean
)
