package com.danielefavaro.githubplayground.core.common.domain

import com.danielefavaro.githubplayground.core.common.model.NetworkStatusModel
import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityService {
    val networkStatus: Flow<NetworkStatusModel>
}
