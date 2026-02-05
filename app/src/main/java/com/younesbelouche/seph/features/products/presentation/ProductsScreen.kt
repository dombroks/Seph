package com.younesbelouche.seph.features.products.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.younesbelouche.seph.features.products.presentation.components.ProductsTopSearchBar

@Composable
fun ProductsScreen(
    onSearch: (String) -> Unit,
    productsViewModel: ProductsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by productsViewModel.uiState.collectAsStateWithLifecycle()
    ProductsScreenContent(uiState = uiState, modifier = modifier)

}

@Composable
fun ProductsScreenContent(
    uiState: ProductsUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProductsTopSearchBar(
                query = uiState.searchInput,
                onQueryChange = { },
                onSearch = {},
                modifier = modifier,
            )
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {}

    }
}

