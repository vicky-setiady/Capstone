package com.dicoding.capstone.ui.fragment.userfollow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.R
import com.dicoding.capstone.ui.activity.BaseActivity
import com.dicoding.capstone.ui.activity.userdetail.UserDetailActivity
import com.dicoding.capstone.ui.adapter.UserAdapter
import com.dicoding.capstone.ui.fragment.BaseFragment
import com.dicoding.core.data.SourceStatus
import kotlinx.android.synthetic.main.fragment_user_follow.*
import kotlinx.android.synthetic.main.fragment_user_follow.tv_information

private const val ARG_SECTION_NUMBER = "section_number"
private const val ARG_USERNAME = "username"

class UserFollowFragment : BaseFragment() {
    companion object {
        @JvmStatic
        fun newInstance(index: Int, username: String) =
                UserFollowFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_SECTION_NUMBER, index)
                        putString(ARG_USERNAME, username)
                    }
                }
    }

    private val userFollowViewModel: UserFollowViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var userAdapter: UserAdapter
    private lateinit var username: String
    private var isFollowing = false

    override fun getLayoutId(): Int = R.layout.fragment_user_follow

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userFollowViewModel.fetchUserList()
    }

    override fun setupData() {
        arguments?.let {
            val index = it.getInt(ARG_SECTION_NUMBER)
            isFollowing = index == 1
            username = it.getString(ARG_USERNAME).toString()
        }
    }

    override fun setupComponent() {
        setContent(rv_user_follow, ll_information, tv_information, progress_bar)
        userAdapter = UserAdapter { user ->
            val intent = Intent(requireContext(), UserDetailActivity::class.java)
            intent.putExtra(UserDetailActivity.EXTRA_USER, user)
            startActivity(intent)
        }
        with(rv_user_follow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun setupObserver() {
        userFollowViewModel.mMutableLiveDataUserList.observe(this, { sourceStatus ->
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
                        showInformation(
                                true,
                                (requireActivity() as BaseActivity).getErrorMessage(message)
                        )
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