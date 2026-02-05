package com.younesbelouche.seph.core.util

sealed class Failure {
    object Network : Failure()
    object NotFound : Failure()
    object Server : Failure()
    object Unknown : Failure()
}


fun Throwable.toFailure(): Failure =
    when (this) {
        is java.io.IOException -> Failure.Network
        is retrofit2.HttpException -> {
            when (code()) {
                404 -> Failure.NotFound
                in 500..599 -> Failure.Server
                else -> Failure.Unknown
            }
        }

        else -> Failure.Unknown
    }
