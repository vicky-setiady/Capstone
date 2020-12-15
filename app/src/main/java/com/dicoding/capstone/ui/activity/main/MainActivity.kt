package com.dicoding.capstone.ui.activity.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.BaseApplication
import com.dicoding.capstone.R
import com.dicoding.capstone.ui.ViewModelFactory
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.capstone.ui.adapter.UserAdapter
import com.dicoding.capstone.ui.activity.userdetail.UserDetailActivity
import com.dicoding.core.data.SourceStatus
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var userAdapter: UserAdapter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        mainViewModel.fetchUserList(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.fetchUserListBySearch(query, this@MainActivity)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        val menuItem = menu.findItem(R.id.action_search)
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                mainViewModel.fetchUserList(this@MainActivity)
                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
                true
            }
            R.id.action_favorite -> {
                val uri = Uri.parse("dicoding://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> true
        }
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
        mainViewModel.mMutableLiveDataUserList.observe(this, { sourceStatus ->
            if (sourceStatus != null) {
                when (sourceStatus) {
                    is SourceStatus.Loading -> {
                        showLoading(true)
                    }
                    is SourceStatus.Success -> {
                        sourceStatus.data?.let {
                            userAdapter.setData(it)
                            showLoading(false)
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
    }
}