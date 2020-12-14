package com.dicoding.core.data.source.local.room

import androidx.room.*
import com.dicoding.core.data.source.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Vicky Setiady on 2020
 */
@Dao
interface AppDao {
    @Query("SELECT * from tUserEntity ORDER BY login ASC")
    fun getFavoriteUsers(): Flowable<List<UserEntity>>

    @Query("SELECT * from tUserEntity WHERE id = :userId")
    fun getFavoriteUserById(userId: Int): Flowable<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteUser(userEntity: UserEntity)

    @Delete
    fun deleteFavoriteUser(userEntity: UserEntity)
}