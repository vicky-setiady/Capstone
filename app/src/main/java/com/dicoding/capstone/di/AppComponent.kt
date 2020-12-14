package com.dicoding.capstone.di

import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.capstone.ui.activity.main.MainActivity
import com.dicoding.capstone.ui.activity.userdetail.UserDetailActivity
import com.dicoding.capstone.ui.fragment.BaseFragment
import com.dicoding.core.di.CoreComponent
import dagger.Component

/**
 * Created by Vicky Setiady on 2020
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: BaseFragment)
    fun inject(activity: BaseActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: UserDetailActivity)
}