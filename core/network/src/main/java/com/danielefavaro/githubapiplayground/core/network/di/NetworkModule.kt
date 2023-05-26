package com.danielefavaro.githubapiplayground.core.network.di

import android.content.Context
import com.danielefavaro.githubapiplayground.core.network.source.EnvironmentConfig
import com.danielefavaro.githubapiplayground.core.network.source.EnvironmentConfigImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Qualifier
annotation class ApiAuthInterceptor

@Qualifier
annotation class ApiVersionInterceptor

@Qualifier
annotation class LoggingInterceptor

private const val BEARER_TOKEN_HEADER = "Authorization"
private const val API_VERSION_HEADER = "X-GitHub-Api-Version"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideEnvironmentConfig(@ApplicationContext context: Context): EnvironmentConfig =
        EnvironmentConfigImpl(
            context = context
        )

    @LoggingInterceptor
    @Singleton
    @Provides
    fun provideLoggingInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
        HttpLoggingInterceptor().setLevel(environmentConfig.logLevel)

    @ApiAuthInterceptor
    @Singleton
    @Provides
    fun provideApiAuthInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header(BEARER_TOKEN_HEADER, "Bearer ${environmentConfig.apiKey}")
                .build()

            chain.proceed(request)
        }

    @ApiVersionInterceptor
    @Singleton
    @Provides
    fun provideApiVersionInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .header(API_VERSION_HEADER, environmentConfig.apiVersion)
                .build()

            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun provideAuthOkHttpClient(
        @ApiAuthInterceptor apiAuthInterceptor: Interceptor,
        @ApiVersionInterceptor apiVersionInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(apiAuthInterceptor)
        addInterceptor(apiVersionInterceptor)
        addInterceptor(loggingInterceptor)

        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        connectTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofitClient(
        environmentConfig: EnvironmentConfig,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(environmentConfig.url)
        .build()
}
