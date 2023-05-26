package com.danielefavaro.githubapiplayground.core.network.source

import android.content.Context
import com.danielefavaro.githubapiplayground.core.network.R
import javax.inject.Inject
import okhttp3.logging.HttpLoggingInterceptor

class EnvironmentConfigImpl @Inject constructor(
    context: Context
) : EnvironmentConfig {
    override val url: String = "https://api.github.com/"
    override val apiKey: String = context.getString(R.string.github_api_key)
    override val apiVersion: String = "2022-11-28"
    override val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
}
