package com.dicoding.core.data

import android.annotation.SuppressLint
import com.dicoding.core.data.source.remote.network.ApiResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by Vicky Setiady on 2020
 */
abstract class SourceNetwork<ResultType, RequestType> {

    private val result = PublishSubject.create<SourceStatus<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()

    init {
        fetchFromNetwork()
    }

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun mappingObject(requestType: RequestType): ResultType

    @SuppressLint("CheckResult")
    private fun fetchFromNetwork() {
        val response = createCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .doOnComplete {
                    mCompositeDisposable.dispose()
                }
                .subscribe { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            result.onNext(SourceStatus.Success(mappingObject(response.data)))
                        }
                        is ApiResponse.Empty -> {
                            result.onNext(SourceStatus.Empty())
                        }
                        is ApiResponse.Fail -> {
                            result.onNext(SourceStatus.Fail(response.errorCode.toString(), null))
                        }
                        is ApiResponse.Error -> {
                            result.onNext(SourceStatus.Error(response.message, null))
                        }
                    }
                }
        mCompositeDisposable.add(response)
    }

    fun asFlowAble(): Flowable<SourceStatus<ResultType>> =
            result.toFlowable(BackpressureStrategy.BUFFER)
}