package com.younesbelouche.seph.features.products.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.younesbelouche.seph.features.products.presentation.models.ProductReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi

@Composable
internal fun ProductWithReviewsItem(
    productWithReviews: ProductReviewsUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ProductHeader(
            product = productWithReviews.product,
            onClick = onClick
        )

        AnimatedVisibility(visible = productWithReviews.areReviewsVisible) {
            ReviewsList(reviews = productWithReviews.reviews)
        }

    }
}

@Composable
private fun ReviewsList(reviews: List<ReviewUi>) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp, start = 32.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        reviews.forEach {
            ReviewItem(reviewUi = it)
        }
    }
}

@Composable
private fun RatingStars(
    rating: String,
    modifier: Modifier = Modifier
) {

    val ratingValue = rating.split(" ").firstOrNull()?.toFloatOrNull() ?: 0f
    val fullStars = ratingValue.toInt()
    val halfStar = (ratingValue % 1 >= 0.5f)

    Row(modifier = modifier) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
        }

        if (halfStar) {
            Icon(
                // should be star half
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
        }

        repeat(5 - fullStars - if (halfStar) 1 else 0) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Composable
private fun ReviewItem(reviewUi: ReviewUi) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        reviewUi.authorName?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = reviewUi.text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        RatingStars(reviewUi.rating ?: "")
    }
}

@Composable
private fun ProductHeader(
    product: ProductUi,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { onClick() }
            .padding(8.dp)
    ) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = product.price,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ProductWithReviewsItemPreview() {
    val product = ProductUi(
        id = 1L,
        name = "Wireless Headphones",
        description = "Over-ear, noise-canceling, long battery life",
        price = "$199.99",
        imageSmallUrl = "https://example.com/images/headphones_small.jpg",
        imageLargeUrl = "https://example.com/images/headphones_large.jpg",
        brandName = "SoundMax",
        isProductSet = false,
        isSpecialBrand = true
    )

    val reviews = listOf(
        ReviewUi(authorName = "Alex M.", text = "Amazing sound quality!", rating = "5"),
        ReviewUi(
            authorName = "Jamie",
            text = "Good, but battery could last longer.",
            rating = "4"
        ),
        ReviewUi(authorName = null, text = "Decent for the price.", rating = "3")
    )

    var areReviewsVisible by remember { mutableStateOf(true) }

    val productWithReviews = ProductReviewsUi(
        product = product,
        reviews = reviews,
        areReviewsVisible = areReviewsVisible
    )

    ProductWithReviewsItem(
        productWithReviews = productWithReviews,
        onClick = { areReviewsVisible = !areReviewsVisible }
    )
}
