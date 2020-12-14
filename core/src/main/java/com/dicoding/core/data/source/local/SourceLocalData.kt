package com.dicoding.core.data.source.local

import com.dicoding.core.data.source.local.entity.UserEntity
import com.dicoding.core.data.source.local.room.AppDao
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vicky Setiady on 2020
 */
@Singleton
class SourceLocalData @Inject constructor(private val appDao: AppDao) {

    fun getFavoriteUsers(): Flowable<List<UserEntity>> = appDao.getFavoriteUsers()

    fun getFavoriteUserById(userId: Int): Flowable<UserEntity> = appDao.getFavoriteUserById(userId)

    fun deleteFavoriteUser(userEntity: UserEntity) = appDao.deleteFavoriteUser(userEntity)

    fun insertFavoriteUser(userEntity: UserEntity) = appDao.insertFavoriteUser(userEntity)
}