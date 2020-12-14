package com.dicoding.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
data class UserDetailResponse(
    @field:SerializedName("login")
    var login: String? = "",

    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("avatar_url")
    var avatarUrl: String? = "",

    @field:SerializedName("gravatar_id")
    var gravatarId: String? = "",

    @field:SerializedName("url")
    var url: String? = "",

    @field:SerializedName("html_url")
    var htmlUrl: String? = "",

    @field:SerializedName("followers_url")
    var followersUrl: String? = "",

    @field:SerializedName("following_url")
    var followingUrl: String? = "",

    @field:SerializedName("gists_url")
    var gistsUrl: String? = "",

    @field:SerializedName("starred_url")
    var starredUrl: String? = "",

    @field:SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = "",

    @field:SerializedName("organizations_url")
    var organizationsUrl: String? = "",

    @field:SerializedName("repos_url")
    var reposUrl: String? = "",

    @field:SerializedName("events_url")
    var eventsUrl: String? = "",

    @field:SerializedName("received_events_url")
    var receivedEventsUrl: String? = "",

    @field:SerializedName("type")
    var type: String? = "",

    @field:SerializedName("site_admin")
    var siteAdmin: Boolean,

    @field:SerializedName("name")
    var name: String? = "",

    @field:SerializedName("company")
    var company: String? = "",

    @field:SerializedName("blog")
    var blog: String? = "",

    @field:SerializedName("location")
    var location: String? = "",

    @field:SerializedName("email")
    var email: String? = "",

    @field:SerializedName("hireable")
    var hireable: String? = "",

    @field:SerializedName("bio")
    var bio: String? = "",

    @field:SerializedName("twitter_username")
    var twitterUsername: String? = "",

    @field:SerializedName("public_repos")
    var publicRepos: Int,

    @field:SerializedName("public_gists")
    var public_gists: Int,

    @field:SerializedName("followers")
    var followers: Int,

    @field:SerializedName("following")
    var following: Int,

    @field:SerializedName("created_at")
    var createdAt: String? = "",

    @field:SerializedName("updated_at")
    var updatedAt: String? = ""
) : Parcelable