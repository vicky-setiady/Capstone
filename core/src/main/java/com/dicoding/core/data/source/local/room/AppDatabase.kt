package com.dicoding.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.source.local.entity.UserEntity

/**
 * Created by Vicky Setiady on 2020
 */
@Database(
    entities = [
        (UserEntity::class)
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}