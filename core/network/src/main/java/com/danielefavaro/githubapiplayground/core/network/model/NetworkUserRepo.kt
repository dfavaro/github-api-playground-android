package com.danielefavaro.githubapiplayground.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkUserRepo(
    @SerializedName("id")
    val id: Long,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("private")
    val isPrivate: Boolean,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("fork")
    val fork: Boolean,

    @SerializedName("url")
    val url: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("pushed_at")
    val pushedAt: String,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("size")
    val size: Int,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("watchers_count")
    val watchersCount: Int,

    @SerializedName("language")
    val language: String?,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("archived")
    val archived: Boolean,

    @SerializedName("disabled")
    val disabled: Boolean,

    @SerializedName("open_issues_count")
    val openIssuesCount: Int,

    @SerializedName("license")
    val license: License?,

    @SerializedName("default_branch")
    val defaultBranch: String
) {
    data class Owner(
        @SerializedName("login")
        val login: String,

        @SerializedName("id")
        val id: Int,

        @SerializedName("node_id")
        val nodeId: String,

        @SerializedName("avatar_url")
        val avatarUrl: String,

        @SerializedName("gravatar_id")
        val gravatarId: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("html_url")
        val htmlUrl: String,

        @SerializedName("followers_url")
        val followersUrl: String,

        @SerializedName("following_url")
        val followingUrl: String,

        @SerializedName("gists_url")
        val gistsUrl: String,

        @SerializedName("starred_url")
        val starredUrl: String,

        @SerializedName("subscriptions_url")
        val subscriptionsUrl: String,

        @SerializedName("organizations_url")
        val organizationsUrl: String,

        @SerializedName("repos_url")
        val reposUrl: String,

        @SerializedName("events_url")
        val eventsUrl: String,

        @SerializedName("received_events_url")
        val receivedEventsUrl: String,

        @SerializedName("type")
        val type: String,

        @SerializedName("site_admin")
        val siteAdmin: Boolean
    )

    data class License(
        @SerializedName("key")
        val key: String,

        @SerializedName("name")
        val name: String
    )
}
