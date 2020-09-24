package com.androidarchitecture.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidarchitecture.R
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.utilities.ImageLoader
import com.androidarchitecture.utilities.inflate
import kotlinx.android.synthetic.main.adapter_user.view.*

class UsersListAdapter(
    private val imageLoader: ImageLoader,
    val userClickListener: (User) -> Unit
) :
    ListAdapter<User, UsersListAdapter.UsersListViewHolder>(DIFF_UTIL) {

    companion object {
        var DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.image == newItem.image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersListViewHolder(parent.inflate(R.layout.adapter_user))

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { userClickListener(currentList[layoutPosition]) }
        }

        fun bind(user: User) {
            itemView.apply {
                tvUsername.text = user.name
                tvEmail.text = user.email
                imageLoader.loadCircularImage(ivUserProfile, user.image)
            }
        }
    }
}
