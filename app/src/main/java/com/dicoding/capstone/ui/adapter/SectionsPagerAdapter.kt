package com.dicoding.capstone.ui.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.capstone.R
import com.dicoding.capstone.ui.fragment.userfollow.UserFollowFragment

/**
 * Created by Vicky Setiady on 2020
 */
class SectionsPagerAdapter(
    private val mContext: Context,
    private val username: String,
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val mTabTitleList =
        intArrayOf(R.string.user_detail_follower_text, R.string.user_detail_following_text)

    override fun getCount(): Int {
        return mTabTitleList.size
    }

    override fun getItem(position: Int): Fragment {
        return UserFollowFragment.newInstance(position, username)
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(mTabTitleList[position])
    }

}