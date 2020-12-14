package com.dicoding.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "login")
    var login: String? = "",

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = "",

    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String? = "",

    @ColumnInfo(name = "url")
    var url: String? = "",

    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = "",

    @ColumnInfo(name = "followers_url")
    var followersUrl: String? = "",

    @ColumnInfo(name = "following_url")
    var followingUrl: String? = "",

    @ColumnInfo(name = "gists_url")
    var gistsUrl: String? = "",

    @ColumnInfo(name = "starred_url")
    var starredUrl: String? = "",

    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String? = "",

    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String? = "",

    @ColumnInfo(name = "repos_url")
    var reposUrl: String? = "",

    @ColumnInfo(name = "events_url")
    var eventsUrl: String? = "",

    @ColumnInfo(name = "received_events_url")
    var receivedEventsUrl: String? = "",

    @ColumnInfo(name = "type")
    var type: String? = "",

    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean,

    @ColumnInfo(name = "name")
    var name: String? = "",

    @ColumnInfo(name = "company")
    var company: String? = "",

    @ColumnInfo(name = "blog")
    var blog: String? = "",

    @ColumnInfo(name = "location")
    var location: String? = "",

    @ColumnInfo(name = "email")
    var email: String? = "",

    @ColumnInfo(name = "hireable")
    var hireable: String? = "",

    @ColumnInfo(name = "bio")
    var bio: String? = "",

    @ColumnInfo(name = "twitter_username")
    var twitterUsername: String? = "",

    @ColumnInfo(name = "public_repos")
    var publicRepos: Int,

    @ColumnInfo(name = "public_gists")
    var public_gists: Int,

    @ColumnInfo(name = "followers")
    var followers: Int,

    @ColumnInfo(name = "following")
    var following: Int,

    @ColumnInfo(name = "created_at")
    var createdAt: String? = "",

    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = ""
) : Parcelable {
    companion object {
        const val TABLE_NAME = "tUserEntity"
    }
}