package com.younesbelouche.seph.features.products.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun EmptyState(
    searchQuery: String,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = when {
                    errorMessage != null -> "Oops! Something went wrong"
                    searchQuery.isNotBlank() -> "No products found"
                    else -> "No products available"
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = when {
                    errorMessage != null -> errorMessage
                    searchQuery.isNotBlank() -> "Try adjusting your search to find what you're looking for"
                    else -> "Check back later for new products"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, name = "Empty State - No Products")
@Composable
private fun EmptyStateNoProductsPreview() {
    MaterialTheme {
        EmptyState(
            searchQuery = "",
            errorMessage = null
        )
    }
}

@Preview(showBackground = true, name = "Empty State - No Search Results")
@Composable
private fun EmptyStateNoSearchResultsPreview() {
    MaterialTheme {
        EmptyState(
            searchQuery = "wireless headphones",
            errorMessage = null
        )
    }
}

@Preview(showBackground = true, name = "Empty State - Error")
@Composable
private fun EmptyStateErrorPreview() {
    MaterialTheme {
        EmptyState(
            searchQuery = "",
            errorMessage = "Unable to connect to the server. Please check your internet connection."
        )
    }
}

