package com.dicoding.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.core.BuildConfig
import com.dicoding.core.data.source.local.room.AppDao
import com.dicoding.core.data.source.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

/**
 * Created by Vicky Setiady on 2020
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, BuildConfig.DB_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideAppDao(database: AppDatabase): AppDao = database.appDao()
}