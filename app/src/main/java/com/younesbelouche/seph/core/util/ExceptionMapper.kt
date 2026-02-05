package com.younesbelouche.seph.core.util

fun Throwable.toDataFailure(): DataFailure =
    when (this) {
        is java.io.IOException -> DataFailure.Network
        is retrofit2.HttpException -> {
            when (code()) {
                404 -> DataFailure.NotFound
                in 500..599 -> DataFailure.Server
                else -> DataFailure.Unknown
            }
        }
        else -> DataFailure.Unknown
    }
