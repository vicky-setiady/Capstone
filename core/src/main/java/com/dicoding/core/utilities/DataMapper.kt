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
        input.map { userResponse ->
            var login = ""
            userResponse.login?.let {
                login = it
            }
            var avatarUrl = ""
            userResponse.avatarUrl?.let {
                avatarUrl = it
            }
            User(
                id = userResponse.id,
                login = login,
                avatarUrl = avatarUrl,
            )
        }

    fun mapUserEntityToUser(input: List<UserEntity>): List<User> =
        input.map { userResponse ->
            var login = ""
            userResponse.login?.let {
                login = it
            }
            var avatarUrl = ""
            userResponse.avatarUrl?.let {
                avatarUrl = it
            }
            User(
                id = userResponse.id,
                login = login,
                avatarUrl = avatarUrl,
            )
        }

    fun mapUserDetailResponseToUserDetail(input: UserDetailResponse): UserDetail {
        var login = ""
        input.login?.let {
            login = it
        }
        var avatarUrl = ""
        input.avatarUrl?.let {
            avatarUrl = it
        }
        var name = ""
        input.name?.let {
            name = it
        }
        var company = ""
        input.company?.let {
            company = it
        }
        var location = ""
        input.location?.let {
            location = it
        }
        return UserDetail(
            login = login,
            avatarUrl = avatarUrl,
            isFavorite = false,
            id = input.id,
            name = name,
            company = company,
            location = location,
            publicRepos = input.publicRepos,
            followers = input.followers,
            following = input.following,
        )
    }


    fun mapUserDetailToUserEntity(input: UserDetail): UserEntity =
        UserEntity(
            login = input.login,
            avatarUrl = input.avatarUrl,
            id = input.id,
            isFavorite = input.isFavorite,
        )
}