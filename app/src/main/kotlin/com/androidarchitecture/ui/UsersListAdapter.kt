package com.androidarchitecture.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidarchitecture.R
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.utilities.Imagify
import com.androidarchitecture.utilities.inflate
import kotlinx.android.synthetic.main.adapter_user.view.ivUserProfile
import kotlinx.android.synthetic.main.adapter_user.view.tvUsername

class UsersListAdapter(private val list: List<Users>, val userClickListener: OnUserClickListener) :
    RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersListViewHolder(parent.inflate(R.layout.adapter_user))

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.apply {
            tvUsername.text = data.name
            when (position % 5) {
                1 -> Imagify.loadCircularImage(ivUserProfile, data.image)
                2 -> Imagify.loadRoundedCornersImage(ivUserProfile, data.image, 15f)
                3 -> Imagify.loadBlurImage(ivUserProfile, data.image)
                4 -> Imagify.loadGreyScaleImage(ivUserProfile, data.image)
                else -> Imagify.loadImage(ivUserProfile, data.image)
            }
        }
    }

    override fun getItemCount() = list.size

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { userClickListener.onUserClicked(list[layoutPosition].id) }
        }
    }
}

interface OnUserClickListener {
    fun onUserClicked(id: Int)
}
