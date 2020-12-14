package com.dicoding.favorite.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.BaseApplication
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.capstone.ui.activity.userdetail.UserDetailActivity
import com.dicoding.capstone.ui.adapter.UserAdapter
import com.dicoding.favorite.R
import com.dicoding.favorite.ViewModelFactory
import com.dicoding.favorite.di.DaggerFavoriteComponent
import kotlinx.android.synthetic.main.activity_user_favorite.*
import javax.inject.Inject

class UserFavoriteActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val userFavoriteViewModel: UserFavoriteViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var userAdapter: UserAdapter

    override fun getLayoutId(): Int = R.layout.activity_user_favorite

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .coreComponent((application as BaseApplication).coreComponent)
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        enableBackButton()
        setToolbarTitle(resources.getString(R.string.user_favorite_title))
        userFavoriteViewModel.getUserFavoriteList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setupData() {}

    override fun setupComponent() {
        setContent(rv_main_user, llInfo, tv_information, progress_bar)
        userAdapter = UserAdapter { user ->
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra(UserDetailActivity.EXTRA_USER, user)
            startActivity(intent)
        }
        with(rv_main_user) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun setupObserver() {
        userFavoriteViewModel.mMutableLiveDataUserList.observe(this, {
            if(it == null){
                showInformation(true)
            }else if(it.isEmpty()){
                showInformation(true)
            }else{
                userAdapter.setData(it)
            }
        })
    }
}