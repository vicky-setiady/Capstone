package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
data class User(
    var id: Int,
    var login: String,
    var nodeId: String,
    var avatarUrl: String,
    var gravatarId: String,
    var url: String,
    var htmlUrl: String,
    var followersUrl: String,
    var followingUrl: String,
    var gistsUrl: String,
    var starredUrl: String,
    var subscriptionsUrl: String,
    var organizationsUrl: String,
    var reposUrl: String,
    var eventsUrl: String,
    var receivedEventsUrl: String,
    var type: String,
    var siteAdmin: Boolean,
    var score: Double,
) : Parcelable