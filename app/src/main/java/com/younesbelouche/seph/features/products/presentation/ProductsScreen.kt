package com.younesbelouche.seph.features.products.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.younesbelouche.seph.features.products.presentation.components.ProductsList
import com.younesbelouche.seph.features.products.presentation.components.ProductsTopSearchBar
import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
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
        modifier = modifier
    )

}

@Composable
fun ProductsScreenContent(
    uiState: ProductsUiState,
    onProductClick: (Long) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            Surface(
                tonalElevation = 4.dp,
                color = MaterialTheme.colorScheme.surface,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = "Products",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ProductsTopSearchBar(
                        query = uiState.searchInput,
                        onQueryChange = onSearchQueryChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        },

        ) { paddingValues ->
        ProductsList(
            productsWithReviews = uiState.productsWithReviews,
            onItemClick = onProductClick,
            modifier = modifier.padding(paddingValues),
        )

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

    val uiState = ProductsUiState(
        searchInput = "",
        productsWithReviews = sampleProducts
    )

    ProductsScreenContent(
        uiState = uiState,
        onProductClick = {},
        onSearchQueryChange = {},
        modifier = Modifier
    )
}


