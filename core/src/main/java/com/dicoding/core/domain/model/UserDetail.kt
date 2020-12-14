package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
data class UserDetail(
    var login: String,
    var avatarUrl: String,
    var isFavorite: Boolean,
    var id: Int,
    var name: String,
    var company: String,
    var location: String,
    var publicRepos: Int,
    var followers: Int,
    var following: Int,
) : Parcelable