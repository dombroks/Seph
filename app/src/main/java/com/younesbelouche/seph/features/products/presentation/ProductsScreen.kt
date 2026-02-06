package com.younesbelouche.seph.features.products.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.younesbelouche.seph.features.products.presentation.components.EmptyState
import com.younesbelouche.seph.features.products.presentation.components.LoadingState
import com.younesbelouche.seph.features.products.presentation.components.ProductsList
import com.younesbelouche.seph.features.products.presentation.components.ProductsTopBar
import com.younesbelouche.seph.features.products.presentation.components.SortReviewsFloatingActionButton
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ProductWithReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi

@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by productsViewModel.uiState.collectAsStateWithLifecycle()
    ProductsScreenContent(
        uiState = uiState,
        onProductClick = productsViewModel::toggleReviewsVisibility,
        onSearchQueryChange = productsViewModel::updateSearchQuery,
        onSortClick = productsViewModel::toggleReviewsSortOption,
        modifier = modifier
    )

}

@Composable
fun ProductsScreenContent(
    uiState: ProductsUiState,
    onProductClick: (Long) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSortClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            ProductsTopBar(
                searchInput = uiState.searchInput,
                onSearchQueryChange = onSearchQueryChange
            )
        },
        floatingActionButton = {
            SortReviewsFloatingActionButton(
                uiState = uiState,
                onSortClick = onSortClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> {
                    LoadingState()
                }

                uiState.productsWithReviews.isEmpty() -> {
                    EmptyState(
                        searchQuery = uiState.searchInput,
                        errorMessage = uiState.errorMessage
                    )
                }

                else -> {
                    ProductsList(
                        productsWithReviews = uiState.productsWithReviews,
                        onItemClick = onProductClick,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ProductsScreenPreview() {
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
        ReviewUi(authorName = "Jamie", text = "Good, but battery could last longer.", rating = "4"),
        ReviewUi(authorName = null, text = "Decent for the price.", rating = "3")
    )

    val sampleProducts = List(10) { index ->
        ProductWithReviewsUi(
            product = baseProduct.copy(
                id = index.toLong(),
                name = "Product ${index + 1}",
                price = "$${199 + index * 10}"
            ),
            reviews = sampleReviews,
            areReviewsVisible = false
        )
    }

    val uiState = ProductsUiState(
        searchInput = "",
        productsWithReviews = sampleProducts
    )

    ProductsScreenContent(
        uiState = uiState,
        onProductClick = {},
        onSearchQueryChange = {},
        onSortClick = {},
        modifier = Modifier
    )
}


