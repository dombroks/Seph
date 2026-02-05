package com.younesbelouche.seph.features.products.data.repositories

import com.younesbelouche.seph.core.util.Result
import com.younesbelouche.seph.features.products.data.datasources.ProductRemoteDataSource
import com.younesbelouche.seph.features.products.data.mappers.ProductMapper
import com.younesbelouche.seph.features.products.data.mappers.ProductReviewsMapper
import com.younesbelouche.seph.features.products.domain.entities.Product
import com.younesbelouche.seph.features.products.domain.entities.ProductReviews
import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.map

class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductRemoteDataSource,
) : ProductsRepository {
    override fun getProducts(): Flow<Result<List<Product>>> = flow {
        emit(Result.Loading)

        emit(Result.Success(ProductMapper.fromDtoList(productsRemoteDataSource.getProducts())))

    }.catch { e ->
        emit(Result.Error(e))
    }


    override fun getProductReviews(): Flow<Result<List<ProductReviews>>> = flow {
        emit(Result.Loading)

        emit(Result.Success(ProductReviewsMapper.fromDtoList(productsRemoteDataSource.getProductReviews())))

    }.catch { e ->
        emit(Result.Error(e))
    }
}