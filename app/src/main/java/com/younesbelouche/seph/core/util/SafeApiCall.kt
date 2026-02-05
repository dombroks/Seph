package com.younesbelouche.seph.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> safeApiCall(
    crossinline call: suspend () -> T
) =
    withContext(Dispatchers.IO) {
        try {
            call()
        } catch (t: Throwable) {
            t
        }
    }
