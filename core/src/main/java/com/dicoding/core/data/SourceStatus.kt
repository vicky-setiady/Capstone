package com.dicoding.core.data

/**
 * Created by Vicky Setiady on 2020
 */
sealed class SourceStatus<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : SourceStatus<T>(data)
    class Loading<T>(data: T? = null) : SourceStatus<T>(data)
    class Error<T>(message: String, data: T? = null) : SourceStatus<T>(data, message)
    class Fail<T>(errorCode: String, data: T? = null) : SourceStatus<T>(data, errorCode)
    class Empty<Nothing> : SourceStatus<Nothing>()
}