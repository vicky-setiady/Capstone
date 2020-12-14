package com.dicoding.capstone

import android.app.Application
import com.dicoding.capstone.di.AppComponent
import com.dicoding.capstone.di.DaggerAppComponent
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.core.di.CoreComponent
import com.dicoding.core.di.DaggerCoreComponent

/**
 * Created by Vicky Setiady on 2020
 */
open class BaseApplication : Application() {

    companion object {
        private var mCurrentActivity: BaseActivity? = null

        fun setCurrentActivity(mActivity: BaseActivity?) {
            mActivity?.let {
                this.mCurrentActivity = it
            }
        }

        fun getCurrentActivity(): BaseActivity? = mCurrentActivity
    }

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}