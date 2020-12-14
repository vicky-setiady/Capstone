package com.dicoding.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.core.domain.usecase.IUserUseCase
import com.dicoding.favorite.activity.UserFavoriteViewModel
import com.dicoding.favorite.di.FavoriteScope
import javax.inject.Inject

/**
 * Created by Vicky Setiady on 2020
 */
@FavoriteScope
class ViewModelFactory @Inject constructor(private val userUseCase: IUserUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(UserFavoriteViewModel::class.java) -> {
                UserFavoriteViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}