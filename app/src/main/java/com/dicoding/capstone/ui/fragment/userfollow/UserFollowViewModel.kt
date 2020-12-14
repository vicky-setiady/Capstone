package com.dicoding.capstone.ui.fragment.userfollow

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
class UserFollowViewModel(private val userUseCase: IUserUseCase) : ViewModel() {
    var mMutableLiveDataUserList: MutableLiveData<SourceStatus<List<User>>> = MutableLiveData()

    fun fetchUserList() {
        BaseApplication.getCurrentActivity()?.let { currentActivity ->
            mMutableLiveDataUserList.postValue(SourceStatus.Loading(null))
            LiveDataReactiveStreams.fromPublisher(userUseCase.fetchAllUserList())
                .observe(currentActivity, {
                    mMutableLiveDataUserList.postValue(it)
                })
        }
    }
}