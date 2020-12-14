package com.dicoding.core.data.source.remote.network

import com.dicoding.core.BuildConfig
import com.dicoding.core.data.source.remote.response.UserListResponse
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Vicky Setiady on 2020
 */
interface IApiService {
    @GET("users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserList(): Flowable<Response<JsonArray>>

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserListBySearch(@Query("q") username: String): Flowable<Response<UserListResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserListFollowing(@Path("username") username: String): Flowable<Response<JsonArray>>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserListFollowers(@Path("username") username: String): Flowable<Response<JsonArray>>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUserDetail(@Path("username") username: String): Flowable<Response<JsonElement>>
}