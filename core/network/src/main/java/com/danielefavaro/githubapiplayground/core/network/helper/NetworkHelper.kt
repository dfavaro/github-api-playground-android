package com.danielefavaro.githubapiplayground.core.network.helper

import com.danielefavaro.githubapiplayground.core.model.exception.ConnectionError
import com.danielefavaro.githubapiplayground.core.model.exception.GenericError
import com.danielefavaro.githubapiplayground.core.model.exception.NotFoundError
import com.danielefavaro.githubapiplayground.core.model.exception.TimeoutError
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        try {
            apiCall.invoke()
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutException, is SocketTimeoutException -> {
                    throw TimeoutError()
                }

                is IOException -> {
                    throw ConnectionError()
                }

                is HttpException -> {
                    // TODO check for error codes
                    throw NotFoundError()
                }

                else -> {
                    throw GenericError()
                }
            }
        }
    }
}
