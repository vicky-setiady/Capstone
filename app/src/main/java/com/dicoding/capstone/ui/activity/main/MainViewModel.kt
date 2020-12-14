package com.dicoding.capstone.ui.activity.main

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.BaseApplication
import com.dicoding.core.data.SourceStatus
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.usecase.IUserUseCase

/**
 * Created by Vicky Setiady on 2020
 */
class MainViewModel(private val userUseCase: IUserUseCase) : ViewModel() {

    var mMutableLiveDataUserList: MutableLiveData<SourceStatus<List<User>>> = MutableLiveData()

    fun fetchUserList() {
        BaseApplication.getCurrentActivity()?.let { currentActivity ->
            mMutableLiveDataUserList.postValue(SourceStatus.Loading(null))
            LiveDataReactiveStreams.fromPublisher(userUseCase.fetchAllUserList()).observe(currentActivity, {
                mMutableLiveDataUserList.postValue(it)
            })
        }
    }

    fun fetchUserListBySearch(username: String) {
        BaseApplication.getCurrentActivity()?.let { currentActivity ->
            mMutableLiveDataUserList.postValue(SourceStatus.Loading(null))
            LiveDataReactiveStreams.fromPublisher(userUseCase.fetchUserListByUsername(username)).observe(currentActivity, {
                mMutableLiveDataUserList.postValue(it)
            })
        }
    }
}