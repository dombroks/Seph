package com.younesbelouche.seph.features.products.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.younesbelouche.seph.core.theme.StarGold
import com.younesbelouche.seph.features.products.presentation.models.ProductUi
import com.younesbelouche.seph.features.products.presentation.models.ProductWithReviewsUi
import com.younesbelouche.seph.features.products.presentation.models.ReviewUi
import kotlin.math.roundToInt

@Composable
internal fun ProductWithReviewsItem(
    productWithReviews: ProductWithReviewsUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductHeader(
                product = productWithReviews.product,
                reviewCount = productWithReviews.reviews.size,
                areReviewsVisible = productWithReviews.areReviewsVisible,
                onClick = onClick
            )

            AnimatedVisibility(visible = productWithReviews.areReviewsVisible) {
                Column {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    ReviewsList(reviews = productWithReviews.reviews)
                }
            }
        }

    }
}

@Composable
private fun ReviewsList(reviews: List<ReviewUi>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Reviews (${reviews.size})",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        reviews.forEach { review ->
            ReviewItem(reviewUi = review)
        }
    }
}

@Composable
private fun RatingStars(
    rating: String,
    modifier: Modifier = Modifier
) {
    val ratingValue = rating.split(" ").firstOrNull()?.toFloatOrNull() ?: 0f
    val stars = ratingValue.roundToInt().coerceIn(0, 5)

    Row(modifier = modifier) {
        repeat(stars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = StarGold,
                modifier = Modifier.size(16.dp)
            )
        }

        repeat(5 - stars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}



@Composable
private fun ReviewItem(reviewUi: ReviewUi) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                reviewUi.authorName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                RatingStars(reviewUi.rating ?: "")
            }

            Text(
                text = reviewUi.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductHeader(
    product: ProductUi,
    reviewCount: Int,
    areReviewsVisible: Boolean,
    onClick: () -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (areReviewsVisible) 180f else 0f,
        label = "dropdown rotation"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "•",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = product.brandName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (reviewCount > 0) {
                Text(
                    text = "$reviewCount ${if (reviewCount == 1) "review" else "reviews"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = if (areReviewsVisible) "Collapse reviews" else "Expand reviews",
            modifier = Modifier
                .size(24.dp)
                .rotate(rotationAngle),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
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

    val productWithReviews = ProductWithReviewsUi(
        product = product,
        reviews = reviews,
        areReviewsVisible = areReviewsVisible
    )

    ProductWithReviewsItem(
        productWithReviews = productWithReviews,
        onClick = { areReviewsVisible = !areReviewsVisible }
    )
}
