package com.dicoding.capstone.ui.activity.userdetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.BaseApplication
import com.dicoding.capstone.R
import com.dicoding.capstone.ui.ViewModelFactory
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.capstone.ui.adapter.SectionsPagerAdapter
import com.dicoding.core.data.SourceStatus
import com.dicoding.core.domain.model.User
import com.dicoding.core.domain.model.UserDetail
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.util.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_USER_LOGIN = "extra_user_login"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val userDetailViewModel: UserDetailViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var currentUserDetail: UserDetail
    private var loginId: String? = null
    private var isFavorite = false

    override fun getLayoutId(): Int = R.layout.activity_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableBackButton()
        setZeroToolbarElevation()
        setToolbarTitle(getUsername())
        userDetailViewModel.fetchUserDetail(getUsername())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.details_menu, menu)
        setFavoriteButton()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "https://github.com/".plus(getUsername()))
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setupData() {
        loginId = intent.getStringExtra(EXTRA_USER_LOGIN)
        if (loginId == null) {
            val user = intent.getParcelableExtra<User>(EXTRA_USER)
            loginId = user?.login
        }
    }

    override fun setupComponent() {
        setContent(ll_content, ll_information, tv_information, progress_bar)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, getUsername(), supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        btn_favorite.setOnClickListener {
            isFavorite = !isFavorite
            currentUserDetail.isFavorite = isFavorite
            if (isFavorite) {
                userDetailViewModel.setUserFavorite(currentUserDetail)
            } else {
                userDetailViewModel.deleteUserFavorite(currentUserDetail)
            }
            setFavoriteButton()
        }
    }

    override fun setupObserver() {
        userDetailViewModel.mMutableLiveDataUserDetail.observe(this, { sourceStatus ->
            if (sourceStatus != null) {
                when (sourceStatus) {
                    is SourceStatus.Loading -> {
                        showLoading(true)
                    }
                    is SourceStatus.Success -> {
                        sourceStatus.data?.let {
                            setData(it)
                            showLoading(false)
                            userDetailViewModel.getUserEntityById(it.id)
                        }
                    }
                    is SourceStatus.Fail -> {
                        var message = ""
                        sourceStatus.message?.let {
                            message = it
                        }
                        showInformation(true, getErrorMessage(message))
                    }
                    is SourceStatus.Error -> {
                        var message = ""
                        sourceStatus.message?.let {
                            message = it
                        }
                        showInformation(true, message)
                    }
                    else -> showInformation(true)
                }
            }
        })
        userDetailViewModel.mMutableLiveDataUserEntity.observe(this, { userEntity ->
            isFavorite = userEntity.isFavorite
            setFavoriteButton()
        })
    }

    private fun setFavoriteButton() {
        if(isFavorite)
            btn_favorite.setImageResource(R.drawable.baseline_favorite_white_24)
        else
            btn_favorite.setImageResource(R.drawable.baseline_favorite_border_white_24)
    }

    private fun setData(userDetail: UserDetail) {
        this.currentUserDetail = userDetail
        userDetail.let {
            Glide.with(this)
                    .load(it.avatarUrl)
                    .apply(RequestOptions().override(64, 64))
                    .into(iv_photo)
            tv_name.text = it.name
            tv_company.text = it.company
            tv_location.text = it.location
            tv_repository.text = it.publicRepos.toString()
            tv_follower.text = it.followers.toString()
            tv_following.text = it.following.toString()
        }
    }

    private fun getUsername(): String {
        loginId?.let {
            return it.toLowerCase(Locale.ROOT)
        }
        return ""
    }
}