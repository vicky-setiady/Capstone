package com.dicoding.favorite.di

import com.dicoding.core.domain.usecase.IUserUseCase
import com.dicoding.core.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module

/**
 * Created by Vicky Setiady on 2020
 */

@Module
abstract class FavoriteModule {

    @Binds
    abstract fun provideUserUseCase(userUseCase: UserUseCase): IUserUseCase
}