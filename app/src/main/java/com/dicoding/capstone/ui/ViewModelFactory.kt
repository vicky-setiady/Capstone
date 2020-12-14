package com.dicoding.capstone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.di.AppScope
import com.dicoding.capstone.ui.activity.main.MainViewModel
import com.dicoding.capstone.ui.activity.userdetail.UserDetailViewModel
import com.dicoding.capstone.ui.fragment.userfollow.UserFollowViewModel
import com.dicoding.core.domain.usecase.IUserUseCase
import javax.inject.Inject

/**
 * Created by Vicky Setiady on 2020
 */
@AppScope
class ViewModelFactory @Inject constructor(private val userUseCase: IUserUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> {
                UserDetailViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(UserFollowViewModel::class.java) -> {
                UserFollowViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}