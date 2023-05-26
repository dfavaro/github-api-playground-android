package com.danielefavaro.githubapiplayground.core.network.source

import okhttp3.logging.HttpLoggingInterceptor

interface EnvironmentConfig {
    val url: String
    val apiKey: String
    val apiVersion: String
    val logLevel: HttpLoggingInterceptor.Level
}
