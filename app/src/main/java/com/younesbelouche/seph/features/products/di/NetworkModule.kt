package com.younesbelouche.seph.features.products.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.younesbelouche.seph.core.Constants
import com.younesbelouche.seph.features.products.data.ProductsApi
import com.younesbelouche.seph.features.products.data.interceptors.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCacheInterceptor() = CacheInterceptor()

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L))
            addNetworkInterceptor(cacheInterceptor)
            connectTimeout(3, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(25, TimeUnit.SECONDS)
        }.build()

    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)
}