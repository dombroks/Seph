package com.younesbelouche.seph.features.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.younesbelouche.seph.core.util.Result
import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.usecases.GetProductsUseCase
import com.younesbelouche.seph.features.products.domain.usecases.GetReviewsUseCase
import com.younesbelouche.seph.features.products.presentation.mappers.UiMapper.toUi
import com.younesbelouche.seph.features.products.presentation.models.ProductWithReviewsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getReviewsUseCase: GetReviewsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ProductsUiState(),
    )
    private var allProductsCache: List<ProductWithReviewsUi> = emptyList()

    init {
        loadProductsWithTheirReviews()
        observeSearchQuery()
    }


    private fun observeSearchQuery() {
        uiState
            .map { it.searchInput }
            .debounce(SEARCH_DEBOUNCE_MS)
            .distinctUntilChanged()
            .onEach { query -> filterProductsByQuery(query) }
            .launchIn(viewModelScope)
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchInput = query) }
    }

    private fun filterProductsByQuery(query: String) {
        val filtered = if (query.isBlank()) {
            allProductsCache
        } else {
            allProductsCache.filter { it.matchesSearchQuery(query) }
        }

        _uiState.update { it.copy(productsWithReviews = filtered) }
    }

    private fun ProductWithReviewsUi.matchesSearchQuery(query: String): Boolean {
        return product.name.contains(query, ignoreCase = true)
    }


    private fun loadProductsWithTheirReviews() {
        getProductsUseCase()
            .onEach { productsResult ->
                when (productsResult) {
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = productsResult.exception.message
                            )
                        }
                    }

                    is Result.Success -> {
                        // Products fetched successfully, now we get reviews
                        fetchReviewsForProducts(productsResult.data)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchReviewsForProducts(products: List<Product>) {
        getReviewsUseCase()
            .onEach { reviewsResult ->
                when (reviewsResult) {
                    is Result.Loading -> {
                        // Keep loading state
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = reviewsResult.exception.message
                            )
                        }
                    }

                    is Result.Success -> {
                        val productsWithReviews = products.mapNotNull { product ->
                            val productReviews = reviewsResult.data.find {
                                it.productId == product.id
                            }

                            productReviews?.let {
                                toUi(product, it)
                            }
                        }

                        allProductsCache = productsWithReviews
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                productsWithReviews = productsWithReviews,
                                errorMessage = null
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
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


    companion object {
        private const val SEARCH_DEBOUNCE_MS = 300L
    }

}



