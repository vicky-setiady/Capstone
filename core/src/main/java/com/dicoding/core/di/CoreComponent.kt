package com.dicoding.core.di

import android.content.Context
import com.dicoding.core.data.ISourceRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Vicky Setiady on 2020
 */
@Singleton
@Component(
        modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): ISourceRepository
}