package com.younesbelouche.seph.features.products.domain.entities

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imageSmallUrl: String,
    val imageLargeUrl: String,
    val brandId: String,
    val brandName: String,
    val isProductSet: Boolean,
    val isSpecialBrand: Boolean
)

