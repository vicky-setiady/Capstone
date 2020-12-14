package com.dicoding.core.domain.usecase

import com.dicoding.core.data.ISourceRepository
import com.dicoding.core.data.SourceStatus
import com.dicoding.core.data.source.local.entity.UserEntity
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.model.UserDetail
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Vicky Setiady on 2020
 */
class UserUseCase @Inject constructor(private val sourceRepository: ISourceRepository) :
    IUserUseCase {

    override fun getAllUserFavoriteList(): Flowable<List<User>> =
        sourceRepository.getAllUserFavoriteList()

    override fun getUserFavoriteByUserId(userId: Int): Flowable<UserEntity> =
        sourceRepository.getUserFavoriteByUserId(userId)

    override fun insertUserFavorite(userDetail: UserDetail) =
        sourceRepository.insertUserFavorite(userDetail)

    override fun deleteUserFavorite(userDetail: UserDetail) =
        sourceRepository.deleteUserFavorite(userDetail)

    override fun fetchAllUserList(): Flowable<SourceStatus<List<User>>> =
        sourceRepository.fetchAllUserList()

    override fun fetchUserListByUsername(username: String): Flowable<SourceStatus<List<User>>> =
        sourceRepository.fetchUserListByUsername(username)

    override fun fetchUserFollowingListByUsername(username: String): Flowable<SourceStatus<List<User>>> =
        sourceRepository.fetchUserFollowingListByUsername(username)

    override fun fetchUserFollowerListByUsername(username: String): Flowable<SourceStatus<List<User>>> =
        sourceRepository.fetchUserFollowerListByUsername(username)

    override fun fetchUserDetailByUsername(username: String): Flowable<SourceStatus<UserDetail>> =
        sourceRepository.fetchUserDetailByUsername(username)
}