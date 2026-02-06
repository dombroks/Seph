package com.younesbelouche.seph.features.products.data.repositories

import app.cash.turbine.test
import com.younesbelouche.seph.core.util.Result
import com.younesbelouche.seph.features.products.data.datasources.ProductRemoteDataSource
import com.younesbelouche.seph.features.products.domain.repositories.ProductsRepository
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var sut: ProductsRepository

    private lateinit var remoteDataSource: ProductRemoteDataSource


    @Before
    fun setUp() {
        remoteDataSource = mockk()
        sut = ProductsRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getProducts emits Loading then Success`() = runTest {
        coEvery { remoteDataSource.getProducts() } returns dummyProducts

        val result = sut.getProducts()
        result.test {
            assert(awaitItem() is Result.Loading)

            val success = awaitItem() as Result.Success
            assert(success.data.isNotEmpty())
            assert(success.data.size == dummyProducts.size)

            awaitComplete()
        }
    }

    @Test
    fun `getProducts emits Loading then Error when exception happens`() = runTest {
        val exception = okio.IOException("Network error")
        coEvery { remoteDataSource.getProducts() } throws exception

        val result = sut.getProducts()
        result.test {
            assert(awaitItem() is Result.Loading)

            val error = awaitItem() as Result.Error
            assert(error.exception == exception)

            awaitComplete()
        }
    }


    @Test
    fun `getProductReviews emits Error when remote fails`() = runTest {
        val exception = okio.IOException("API error")
        coEvery { remoteDataSource.getProductReviews() } throws exception

        val result = sut.getProductReviews()
        result.test {
            sut.getProductReviews().test {
                assert(awaitItem() is Result.Loading)

                val error = awaitItem() as Result.Error
                assert(error.exception == exception)

                awaitComplete()
            }
        }
    }
}