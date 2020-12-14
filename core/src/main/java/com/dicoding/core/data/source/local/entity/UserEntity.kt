package com.dicoding.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "login")
    var login: String? = "",

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = "",

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean,
) : Parcelable {
    companion object {
        const val TABLE_NAME = "tUserEntity"
    }
}