package com.dicoding.core.data

import com.dicoding.core.data.source.local.SourceLocalData
import com.dicoding.core.data.source.local.entity.UserEntity
import com.dicoding.core.data.source.remote.SourceRemoteData
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.UserDetailResponse
import com.dicoding.core.data.source.remote.response.UserResponse
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.model.UserDetail
import com.dicoding.core.utilities.AppExecutors
import com.dicoding.core.utilities.DataMapper
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vicky Setiady on 2020
 */
@Singleton
class SourceRepository @Inject constructor(
        private val sourceRemoteData: SourceRemoteData,
        private val sourceLocalData: SourceLocalData,
        private val appExecutors: AppExecutors
) : ISourceRepository {
    override fun getAllUserFavoriteList(): Flowable<List<User>> =
            sourceLocalData.getFavoriteUsers().map { DataMapper.mapUserEntityToUser(it) }

    override fun getUserFavoriteByUserId(userId: Int): Flowable<UserEntity> =
            sourceLocalData.getFavoriteUserById(userId)

    override fun insertUserFavorite(userDetail: UserDetail) {
        val userEntity = DataMapper.mapUserDetailToUserEntity(userDetail)
        appExecutors.diskIO().execute { sourceLocalData.insertFavoriteUser(userEntity) }
    }

    override fun deleteUserFavorite(userDetail: UserDetail) {
        val userEntity = DataMapper.mapUserDetailToUserEntity(userDetail)
        appExecutors.diskIO().execute { sourceLocalData.deleteFavoriteUser(userEntity) }
    }

    override fun fetchAllUserList(): Flowable<SourceStatus<List<User>>> =
            object : SourceNetwork<List<User>, List<UserResponse>>() {
                override fun createCall(): Flowable<ApiResponse<List<UserResponse>>> =
                        sourceRemoteData.fetchUserList()

                override fun mappingObject(requestType: List<UserResponse>): List<User> {
                    return DataMapper.mapUserResponseToUser(requestType)
                }
            }.asFlowAble()

    override fun fetchUserListByUsername(username: String): Flowable<SourceStatus<List<User>>> =
            object : SourceNetwork<List<User>, List<UserResponse>>() {
                override fun createCall(): Flowable<ApiResponse<List<UserResponse>>> =
                        sourceRemoteData.fetchUserListBySearch(username)

                override fun mappingObject(requestType: List<UserResponse>): List<User> {
                    return DataMapper.mapUserResponseToUser(requestType)
                }
            }.asFlowAble()

    override fun fetchUserFollowingListByUsername(username: String): Flowable<SourceStatus<List<User>>> =
            object : SourceNetwork<List<User>, List<UserResponse>>() {
                override fun createCall(): Flowable<ApiResponse<List<UserResponse>>> =
                        sourceRemoteData.fetchUserListFollowing(username)

                override fun mappingObject(requestType: List<UserResponse>): List<User> {
                    return DataMapper.mapUserResponseToUser(requestType)
                }
            }.asFlowAble()

    override fun fetchUserFollowerListByUsername(username: String): Flowable<SourceStatus<List<User>>> =
            object : SourceNetwork<List<User>, List<UserResponse>>() {
                override fun createCall(): Flowable<ApiResponse<List<UserResponse>>> =
                        sourceRemoteData.fetchUserListFollowers(username)

                override fun mappingObject(requestType: List<UserResponse>): List<User> {
                    return DataMapper.mapUserResponseToUser(requestType)
                }
            }.asFlowAble()

    override fun fetchUserDetailByUsername(username: String): Flowable<SourceStatus<UserDetail>> =
            object : SourceNetwork<UserDetail, UserDetailResponse>() {
                override fun createCall(): Flowable<ApiResponse<UserDetailResponse>> =
                        sourceRemoteData.fetchUserDetail(username)

                override fun mappingObject(requestType: UserDetailResponse): UserDetail {
                    return DataMapper.mapUserDetailResponseToUserDetail(requestType)
                }
            }.asFlowAble()
}