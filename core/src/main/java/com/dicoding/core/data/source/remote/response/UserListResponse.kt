package com.dicoding.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vicky Setiady on 2020
 */
@Parcelize
data class UserListResponse(
    @field:SerializedName("total_count")
    var totalCount: Int,

    @field:SerializedName("incomplete_results")
    var incompleteResults: Boolean,

    @field:SerializedName("items")
    var userList: List<UserResponse>
) : Parcelable