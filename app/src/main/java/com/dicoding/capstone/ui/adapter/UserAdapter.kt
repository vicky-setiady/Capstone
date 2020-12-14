package com.dicoding.capstone.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capstone.R
import com.dicoding.core.domain.model.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by Vicky Setiady on 2020
 */
class UserAdapter(
    private val userClickListener: (User) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val mUsers = ArrayList<User>()

    fun setData(items: List<User>) {
        mUsers.clear()
        mUsers.addAll(items)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .apply(RequestOptions().override(64, 64))
                    .into(iv_photo)
                tv_username.text = "@".plus(user.login)
                setOnClickListener { userClickListener(user) }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val contactView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mUsers[position])
    }
}