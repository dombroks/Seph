package com.younesbelouche.seph.features.products.presentation.models

data class ProductUi(
    val id: Long,
    val name: String,
    val description: String,
    val price: String,
    val imageSmallUrl: String,
    val imageLargeUrl: String,
    val brandName: String,
    val isProductSet: Boolean,
    val isSpecialBrand: Boolean
)

