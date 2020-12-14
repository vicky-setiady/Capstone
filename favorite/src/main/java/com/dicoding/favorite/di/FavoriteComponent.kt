package com.dicoding.favorite.di

import com.dicoding.core.di.CoreComponent
import com.dicoding.favorite.activity.UserFavoriteActivity
import dagger.Component

/**
 * Created by Vicky Setiady on 2020
 */
@FavoriteScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteModule::class]
)
interface FavoriteComponent {

    fun inject(activity: UserFavoriteActivity)
}