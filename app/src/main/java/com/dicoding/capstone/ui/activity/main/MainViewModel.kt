package com.dicoding.capstone.ui.activity.main

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.core.data.SourceStatus
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.usecase.IUserUseCase

/**
 * Created by Vicky Setiady on 2020
 */
class MainViewModel(private val userUseCase: IUserUseCase) : ViewModel() {

    var mMutableLiveDataUserList: MutableLiveData<SourceStatus<List<User>>> = MutableLiveData()

    fun fetchUserList(activity: BaseActivity) {
        mMutableLiveDataUserList.postValue(SourceStatus.Loading(null))
        LiveDataReactiveStreams.fromPublisher(userUseCase.fetchAllUserList()).observe(activity, {
            mMutableLiveDataUserList.postValue(it)
        })
    }

    fun fetchUserListBySearch(username: String, activity: BaseActivity) {
        mMutableLiveDataUserList.postValue(SourceStatus.Loading(null))
        LiveDataReactiveStreams.fromPublisher(userUseCase.fetchUserListByUsername(username)).observe(activity, {
            mMutableLiveDataUserList.postValue(it)
        })
    }
}