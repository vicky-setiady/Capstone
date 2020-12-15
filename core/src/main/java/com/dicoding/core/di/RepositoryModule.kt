package com.dicoding.core.di

import com.dicoding.core.data.ISourceRepository
import com.dicoding.core.data.SourceRepository
import dagger.Binds
import dagger.Module

/**
 * Created by Vicky Setiady on 2020
 */
@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Suppress("unused")
    @Binds
    abstract fun provideRepository(sourceRepository: SourceRepository): ISourceRepository

}