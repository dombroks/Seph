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

fun Throwable.getMessage(): String {
    val failure = this.toFailure()

    return when (failure) {
        Failure.Network -> "Unable to connect to the server. Please check your internet connection."
        Failure.NotFound -> "The server could not find what you were looking for."
        Failure.Server -> "The server encountered an error. Please try again later."
        Failure.Unknown -> "An unknown error occurred. Please try again later."
    }
}