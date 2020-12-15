package com.dicoding.capstone.ui.activity.userdetail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.ui.activity.BaseActivity
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

    fun fetchUserDetail(username: String, activity: BaseActivity) {
        mMutableLiveDataUserDetail.postValue(SourceStatus.Loading(null))
        LiveDataReactiveStreams.fromPublisher(userUseCase.fetchUserDetailByUsername(username))
            .observe(activity, {
                mMutableLiveDataUserDetail.postValue(it)
            })
    }

    fun getUserEntityById(userId: Int, activity: BaseActivity) {
        LiveDataReactiveStreams.fromPublisher(userUseCase.getUserFavoriteByUserId(userId))
            .observe(activity, {
                mMutableLiveDataUserEntity.postValue(it)
            })
    }

    fun setUserFavorite(userDetail: UserDetail) = userUseCase.insertUserFavorite(userDetail)

    fun deleteUserFavorite(userDetail: UserDetail) = userUseCase.deleteUserFavorite(userDetail)
}