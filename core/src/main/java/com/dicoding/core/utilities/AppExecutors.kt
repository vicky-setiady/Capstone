package com.dicoding.core.utilities

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by Vicky Setiady on 2020
 */
class AppExecutors @VisibleForTesting constructor(
        private val diskIO: Executor
) {
    @Inject
    constructor() : this(
            Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO
}