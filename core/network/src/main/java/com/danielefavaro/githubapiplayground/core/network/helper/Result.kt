package com.danielefavaro.githubapiplayground.core.network.helper

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val code: Int, val message: String? = null) : Result<Nothing>()
}
