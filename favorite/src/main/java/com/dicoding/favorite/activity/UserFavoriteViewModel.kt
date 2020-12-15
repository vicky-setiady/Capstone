package com.dicoding.favorite.activity

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.usecase.IUserUseCase

/**
 * Created by Vicky Setiady on 2020
 */
class UserFavoriteViewModel(private val userUseCase: IUserUseCase) : ViewModel() {

    var mMutableLiveDataUserList: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserFavoriteList(activity: BaseActivity) {
        LiveDataReactiveStreams.fromPublisher(userUseCase.getAllUserFavoriteList())
            .observe(activity, {
                mMutableLiveDataUserList.postValue(it)
            })
    }
}