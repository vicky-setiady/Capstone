package com.dicoding.core.data.source.remote.network

/**
 * Created by Vicky Setiady on 2020
 */
sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
    data class Fail(val errorCode: Int) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}