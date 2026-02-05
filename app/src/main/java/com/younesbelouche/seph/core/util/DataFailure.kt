package com.younesbelouche.seph.core.util

sealed class DataFailure {
    object Network : DataFailure()
    object NotFound : DataFailure()
    object Server : DataFailure()
    object Unknown : DataFailure()
}
