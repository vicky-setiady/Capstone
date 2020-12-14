package com.dicoding.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.network.IApiService
import com.dicoding.core.data.source.remote.response.UserDetailResponse
import com.dicoding.core.data.source.remote.response.UserResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vicky Setiady on 2020
 */
@Singleton
class SourceRemoteData @Inject constructor(private val iApiService: IApiService) {
    private val tag = this.javaClass.simpleName

    @SuppressLint("CheckResult")
    fun fetchUserList(): Flowable<ApiResponse<List<UserResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<UserResponse>>>()

        val flowAbleClient = iApiService.getUserList()
        flowAbleClient.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val arrayUserResponse = object : TypeToken<ArrayList<UserResponse>>() {}.type
                        val userList: ArrayList<UserResponse> =
                                Gson().fromJson(response.body(), arrayUserResponse)
                        result.onNext(if (userList.isNullOrEmpty()) ApiResponse.Empty else ApiResponse.Success(userList))
                    } else {
                        result.onNext(ApiResponse.Fail(response.code()))
                        Log.e(tag, response.toString())
                    }
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e(tag, error.toString())
                })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun fetchUserListBySearch(username: String): Flowable<ApiResponse<List<UserResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<UserResponse>>>()

        val flowAbleClient = iApiService.getUserListBySearch(username)
        flowAbleClient.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val userList = response.body()?.userList
                        if (userList.isNullOrEmpty()) {
                            result.onNext(ApiResponse.Empty)
                        } else {
                            result.onNext(ApiResponse.Success(userList))
                        }

                    } else {
                        result.onNext(ApiResponse.Fail(response.code()))
                        Log.e(tag, response.toString())
                    }
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e(tag, error.toString())
                })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun fetchUserListFollowing(username: String): Flowable<ApiResponse<List<UserResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<UserResponse>>>()

        val flowAbleClient = iApiService.getUserListFollowing(username)
        flowAbleClient.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val arrayUserResponse = object : TypeToken<ArrayList<UserResponse>>() {}.type
                        val userList: ArrayList<UserResponse> =
                                Gson().fromJson(response.body(), arrayUserResponse)
                        result.onNext(if (userList.isNullOrEmpty()) ApiResponse.Empty else ApiResponse.Success(userList))
                    } else {
                        result.onNext(ApiResponse.Fail(response.code()))
                        Log.e(tag, response.toString())
                    }
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e(tag, error.toString())
                })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun fetchUserListFollowers(username: String): Flowable<ApiResponse<List<UserResponse>>> {
        val result = PublishSubject.create<ApiResponse<List<UserResponse>>>()

        val flowAbleClient = iApiService.getUserListFollowers(username)
        flowAbleClient.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val arrayUserResponse = object : TypeToken<ArrayList<UserResponse>>() {}.type
                        val userList: ArrayList<UserResponse> =
                                Gson().fromJson(response.body(), arrayUserResponse)
                        result.onNext(if (userList.isNullOrEmpty()) ApiResponse.Empty else ApiResponse.Success(userList))
                    } else {
                        result.onNext(ApiResponse.Fail(response.code()))
                        Log.e(tag, response.toString())
                    }
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e(tag, error.toString())
                })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun fetchUserDetail(username: String): Flowable<ApiResponse<UserDetailResponse>> {
        val result = PublishSubject.create<ApiResponse<UserDetailResponse>>()

        val flowAbleClient = iApiService.getUserDetail(username)
        flowAbleClient.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val userDetailResponse: UserDetailResponse =
                                Gson().fromJson(response.body(), UserDetailResponse::class.java)
                        result.onNext(ApiResponse.Success(userDetailResponse))
                    } else {
                        result.onNext(ApiResponse.Fail(response.code()))
                        Log.e(tag, response.toString())
                    }
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e(tag, error.toString())
                })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}