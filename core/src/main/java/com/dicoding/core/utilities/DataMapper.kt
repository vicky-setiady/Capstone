package com.dicoding.core.utilities

import com.dicoding.core.data.source.local.entity.UserEntity
import com.dicoding.core.data.source.remote.response.UserDetailResponse
import com.dicoding.core.data.source.remote.response.UserResponse
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.model.UserDetail

/**
 * Created by Vicky Setiady on 2020
 */
object DataMapper {
    fun mapUserResponseToUser(input: List<UserResponse>): List<User> =
        input.map {
            User(
                id = it.id,
                login = if (it.login != null) it.login!! else "",
                nodeId = if (it.nodeId != null) it.nodeId!! else "",
                avatarUrl = if (it.avatarUrl != null) it.avatarUrl!! else "",
                gravatarId = if (it.gravatarId != null) it.gravatarId!! else "",
                url = if (it.url != null) it.url!! else "",
                htmlUrl = if (it.htmlUrl != null) it.htmlUrl!! else "",
                followersUrl = if (it.followersUrl != null) it.followersUrl!! else "",
                followingUrl = if (it.followingUrl != null) it.followingUrl!! else "",
                gistsUrl = if (it.gistsUrl != null) it.gistsUrl!! else "",
                starredUrl = if (it.starredUrl != null) it.starredUrl!! else "",
                subscriptionsUrl = if (it.subscriptionsUrl != null) it.subscriptionsUrl!! else "",
                organizationsUrl = if (it.organizationsUrl != null) it.organizationsUrl!! else "",
                reposUrl = if (it.reposUrl != null) it.reposUrl!! else "",
                eventsUrl = if (it.eventsUrl != null) it.eventsUrl!! else "",
                receivedEventsUrl = if (it.receivedEventsUrl != null) it.receivedEventsUrl!! else "",
                type = if (it.type != null) it.type!! else "",
                siteAdmin = it.siteAdmin,
                score = it.score,
            )
        }

    fun mapUserEntityToUser(input: List<UserEntity>): List<User> =
        input.map {
            User(
                id = it.id,
                login = if (it.login != null) it.login!! else "",
                nodeId = "",
                avatarUrl = if (it.avatarUrl != null) it.avatarUrl!! else "",
                gravatarId = if (it.gravatarId != null) it.gravatarId!! else "",
                url = if (it.url != null) it.url!! else "",
                htmlUrl = if (it.htmlUrl != null) it.htmlUrl!! else "",
                followersUrl = if (it.followersUrl != null) it.followersUrl!! else "",
                followingUrl = if (it.followingUrl != null) it.followingUrl!! else "",
                gistsUrl = if (it.gistsUrl != null) it.gistsUrl!! else "",
                starredUrl = if (it.starredUrl != null) it.starredUrl!! else "",
                subscriptionsUrl = if (it.subscriptionsUrl != null) it.subscriptionsUrl!! else "",
                organizationsUrl = if (it.organizationsUrl != null) it.organizationsUrl!! else "",
                reposUrl = if (it.reposUrl != null) it.reposUrl!! else "",
                eventsUrl = if (it.eventsUrl != null) it.eventsUrl!! else "",
                receivedEventsUrl = if (it.receivedEventsUrl != null) it.receivedEventsUrl!! else "",
                type = if (it.type != null) it.type!! else "",
                siteAdmin = it.siteAdmin,
                score = 0.0,
            )
        }

    fun mapUserDetailResponseToUserDetail(input: UserDetailResponse): UserDetail =
        UserDetail(
            login = if (input.login != null) input.login!! else "",
            avatarUrl = if (input.avatarUrl != null) input.avatarUrl!! else "",
            isFavorite = false,
            id = input.id,
            gravatarId = if (input.gravatarId != null) input.gravatarId!! else "",
            url = if (input.url != null) input.url!! else "",
            htmlUrl = if (input.htmlUrl != null) input.htmlUrl!! else "",
            followersUrl = if (input.followersUrl != null) input.followersUrl!! else "",
            followingUrl = if (input.followingUrl != null) input.followingUrl!! else "",
            gistsUrl = if (input.gistsUrl != null) input.gistsUrl!! else "",
            starredUrl = if (input.starredUrl != null) input.starredUrl!! else "",
            subscriptionsUrl = if (input.subscriptionsUrl != null) input.subscriptionsUrl!! else "",
            organizationsUrl = if (input.organizationsUrl != null) input.organizationsUrl!! else "",
            reposUrl = if (input.reposUrl != null) input.reposUrl!! else "",
            eventsUrl = if (input.eventsUrl != null) input.eventsUrl!! else "",
            receivedEventsUrl = if (input.receivedEventsUrl != null) input.receivedEventsUrl!! else "",
            type = if (input.type != null) input.type!! else "",
            siteAdmin = input.siteAdmin,
            name = if (input.name != null) input.name!! else "",
            company = if (input.company != null) input.company!! else "",
            blog = if (input.blog != null) input.blog!! else "",
            location = if (input.location != null) input.location!! else "",
            email = if (input.email != null) input.email!! else "",
            hireable = if (input.hireable != null) input.hireable!! else "",
            bio = if (input.bio != null) input.bio!! else "",
            twitterUsername = if (input.twitterUsername != null) input.twitterUsername!! else "",
            publicRepos = input.publicRepos,
            public_gists = input.public_gists,
            followers = input.followers,
            following = input.following,
            createdAt = if (input.createdAt != null) input.createdAt!! else "",
            updatedAt = if (input.updatedAt != null) input.updatedAt!! else ""
        )

    fun mapUserDetailToUserEntity(input: UserDetail): UserEntity =
        UserEntity(
            login = input.login,
            avatarUrl = input.avatarUrl,
            id = input.id,
            gravatarId = input.gravatarId,
            url = input.url,
            htmlUrl = input.htmlUrl,
            followersUrl = input.followersUrl,
            followingUrl = input.followingUrl,
            gistsUrl = input.gistsUrl,
            starredUrl = input.starredUrl,
            subscriptionsUrl = input.subscriptionsUrl,
            organizationsUrl = input.organizationsUrl,
            reposUrl = input.reposUrl,
            eventsUrl = input.eventsUrl,
            receivedEventsUrl = input.receivedEventsUrl,
            type = input.type,
            siteAdmin = input.siteAdmin,
            isFavorite = input.isFavorite,
            name = input.name,
            company = input.company,
            blog = input.blog,
            location = input.location,
            email = input.email,
            hireable = input.hireable,
            bio = input.bio,
            twitterUsername = input.twitterUsername,
            publicRepos = input.publicRepos,
            public_gists = input.public_gists,
            followers = input.followers,
            following = input.following,
            createdAt = input.createdAt,
            updatedAt = input.updatedAt
        )
}