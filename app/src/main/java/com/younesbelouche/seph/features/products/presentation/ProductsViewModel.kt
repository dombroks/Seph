package com.younesbelouche.seph.features.products.presentation

import androidx.lifecycle.ViewModel
import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        val baseProduct = ProductUi(
            id = 0L,
            name = "Wireless Headphones",
            description = "Over-ear, noise-canceling, long battery life",
            price = "$199.99",
            imageSmallUrl = "",
            imageLargeUrl = "",
            brandName = "SoundMax",
            isProductSet = false,
            isSpecialBrand = true
        )

        val sampleReviews = listOf(
            ReviewUi(authorName = "Alex M.", text = "Amazing sound quality!", rating = "5"),
            ReviewUi(
                authorName = "Jamie",
                text = "Good, but battery could last longer.",
                rating = "4"
            ),
            ReviewUi(authorName = null, text = "Decent for the price.", rating = "3")
        )

        val sampleProducts = List(10) { index ->
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
        _uiState.update {
            it.copy(productsWithReviews = sampleProducts)
        }
    }

    fun toggleReviewsVisibility(productId: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                productsWithReviews = currentState.productsWithReviews.map { productReview ->
                    if (productReview.product.id == productId) {
                        productReview.copy(areReviewsVisible = !productReview.areReviewsVisible)
                    } else {
                        productReview
                    }
                }
            )
        }
    }

}



