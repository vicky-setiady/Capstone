package com.dicoding.favorite.activity

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.BaseApplication
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.usecase.IUserUseCase

/**
 * Created by Vicky Setiady on 2020
 */
class UserFavoriteViewModel(private val userUseCase: IUserUseCase) : ViewModel() {

    var mMutableLiveDataUserList: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserFavoriteList() {
        BaseApplication.getCurrentActivity()?.let { currentActivity ->
            LiveDataReactiveStreams.fromPublisher(userUseCase.getAllUserFavoriteList())
                .observe(currentActivity, {
                    mMutableLiveDataUserList.postValue(it)
                })
        }
    }
}