package com.danielefavaro.githubplayground.core.common.model

sealed class NetworkStatusModel {
    object Unknown : NetworkStatusModel()
    object Connected : NetworkStatusModel()
    object Disconnected : NetworkStatusModel()
}
