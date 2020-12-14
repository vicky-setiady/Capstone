package com.dicoding.core.data

import com.dicoding.core.data.source.local.entity.UserEntity
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.model.UserDetail
import io.reactivex.Flowable

/**
 * Created by Vicky Setiady on 2020
 */
interface ISourceRepository {
    /** Local Data */
    fun getAllUserFavoriteList(): Flowable<List<User>>

    fun getUserFavoriteByUserId(userId: Int): Flowable<UserEntity>

    fun insertUserFavorite(userDetail: UserDetail)

    fun deleteUserFavorite(userDetail: UserDetail)

    /** Remote Data */
    fun fetchAllUserList(): Flowable<SourceStatus<List<User>>>

    fun fetchUserListByUsername(username: String): Flowable<SourceStatus<List<User>>>

    fun fetchUserFollowingListByUsername(username: String): Flowable<SourceStatus<List<User>>>

    fun fetchUserFollowerListByUsername(username: String): Flowable<SourceStatus<List<User>>>

    fun fetchUserDetailByUsername(username: String): Flowable<SourceStatus<UserDetail>>
}