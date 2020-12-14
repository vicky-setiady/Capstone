package com.dicoding.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
data class UserResponse(
    @field:SerializedName("login")
    var login: String? = "",

    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("node_id")
    var nodeId: String? = "",

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

    @field:SerializedName("score")
    var score: Double,
) : Parcelable