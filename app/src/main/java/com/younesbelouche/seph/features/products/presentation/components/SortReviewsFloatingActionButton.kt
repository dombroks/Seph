package com.younesbelouche.seph.features.products.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.younesbelouche.seph.features.products.presentation.ProductsUiState
import com.younesbelouche.seph.features.products.presentation.ReviewsSortOption


@Composable
internal fun SortReviewsFloatingActionButton(
    uiState: ProductsUiState,
    onSortClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onSortClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = when (uiState.reviewsSortOption) {
                ReviewsSortOption.Best2Worst -> Icons.Default.KeyboardArrowUp
                ReviewsSortOption.Worst2Best -> Icons.Default.KeyboardArrowDown
            },
            contentDescription = when (uiState.reviewsSortOption) {
                ReviewsSortOption.Best2Worst -> "Sort: Best to Worst"
                ReviewsSortOption.Worst2Best -> "Sort: Worst to Best"
            }
        )
    }
}