package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
data class User(
    var id: Int,
    var login: String,
    var avatarUrl: String,
) : Parcelable