package com.dicoding.capstone.ui.activity.userdetail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.BaseApplication
import com.dicoding.core.data.SourceStatus
import com.dicoding.core.data.source.local.entity.UserEntity
import com.dicoding.core.domain.model.UserDetail
import com.dicoding.core.domain.usecase.IUserUseCase

/**
 * Created by Vicky Setiady on 2020
 */
class UserDetailViewModel(private val userUseCase: IUserUseCase) : ViewModel() {
    var mMutableLiveDataUserDetail: MutableLiveData<SourceStatus<UserDetail>> = MutableLiveData()
    var mMutableLiveDataUserEntity: MutableLiveData<UserEntity> = MutableLiveData()

    fun fetchUserDetail(username: String) {
        BaseApplication.getCurrentActivity()?.let { currentActivity ->
            mMutableLiveDataUserDetail.postValue(SourceStatus.Loading(null))
            LiveDataReactiveStreams.fromPublisher(userUseCase.fetchUserDetailByUsername(username))
                .observe(currentActivity, {
                    mMutableLiveDataUserDetail.postValue(it)
                })
        }
    }

    fun getUserEntityById(userId: Int) {
        BaseApplication.getCurrentActivity()?.let { currentActivity ->
            LiveDataReactiveStreams.fromPublisher(userUseCase.getUserFavoriteByUserId(userId))
                .observe(currentActivity, {
                    mMutableLiveDataUserEntity.postValue(it)
                })
        }
    }

    fun setUserFavorite(userDetail: UserDetail) = userUseCase.insertUserFavorite(userDetail)

    fun deleteUserFavorite(userDetail: UserDetail) = userUseCase.deleteUserFavorite(userDetail)
}