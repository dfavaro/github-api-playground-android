package com.danielefavaro.githubapiplayground.core.testing.data

import com.danielefavaro.githubapiplayground.core.network.model.NetworkUserRepo
import io.mockk.every
import io.mockk.mockk

val userReposData = listOf<NetworkUserRepo>(
    mockk(relaxed = true) {
        every { id } returns 0
        every { name } returns "Repo 0"
    },
    mockk(relaxed = true) {
        every { id } returns 1
        every { name } returns "Repo 1"
    },
    mockk(relaxed = true) {
        every { id } returns 2
        every { name } returns "Repo 2"
    },
    mockk(relaxed = true) {
        every { id } returns 3
        every { name } returns "Repo 3"
    }
)
