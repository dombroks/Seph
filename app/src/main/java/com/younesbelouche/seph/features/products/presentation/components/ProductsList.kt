package com.younesbelouche.seph.features.products.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi

@Composable
internal fun ProductsList(
    productsWithReviews: List<ProductReviewsUi>,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(
            items = productsWithReviews,
            key = { it.product.id }
        ) { productWithReviews ->
            ProductWithReviewsItem(
                productWithReviews = productWithReviews,
                onClick = { onItemClick(productWithReviews.product.id) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsListPreview() {
    val baseProduct = ProductUi(
        id = 0L,
        name = "Wireless Headphones",
        description = "Over-ear, noise-canceling, long battery life",
        price = "$199.99",
        imageSmallUrl = "https://example.com/images/headphones_small.jpg",
        imageLargeUrl = "https://example.com/images/headphones_large.jpg",
        brandName = "SoundMax",
        isProductSet = false,
        isSpecialBrand = true
    )

    val sampleReviews = listOf(
        ReviewUi(authorName = "Alex M.", text = "Amazing sound quality!", rating = "5"),
        ReviewUi(authorName = "Jamie", text = "Good, but battery could last longer.", rating = "4"),
        ReviewUi(authorName = null, text = "Decent for the price.", rating = "3")
    )

    var productsWithReviews by remember {
        mutableStateOf(
            List(10) { index ->
                ProductReviewsUi(
                    product = baseProduct.copy(
                        id = index.toLong(),
                        name = "Product ${index + 1}",
                        price = "$${199 + index * 10}"
                    ),
                    reviews = sampleReviews,
                    areReviewsVisible = false
                )
            }
        )
    }
    ProductsList(
        productsWithReviews = productsWithReviews,
        modifier = Modifier,
        onItemClick = { productId ->
            productsWithReviews = productsWithReviews.map {
                if (it.product.id == productId) it.copy(
                    areReviewsVisible = !it.areReviewsVisible
                ) else it
            }
        }
    )
}

