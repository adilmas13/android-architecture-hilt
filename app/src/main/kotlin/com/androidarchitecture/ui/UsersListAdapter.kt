package com.androidarchitecture.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidarchitecture.R
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.utilities.ImageLoader
import com.androidarchitecture.utilities.inflate
import kotlinx.android.synthetic.main.adapter_user.view.*

class UsersListAdapter(
    private val list: List<Users>,
    private val imageLoader: ImageLoader,
    val userClickListener: OnUserClickListener
) :
    RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersListViewHolder(parent.inflate(R.layout.adapter_user))

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.apply {
            tvUsername.text = data.name
            when (position % 5) {
                1 -> imageLoader.loadCircularImage(ivUserProfile, data.image)
                2 -> imageLoader.loadRoundedCornersImage(ivUserProfile, data.image, 15f)
                3 -> imageLoader.loadBlurImage(ivUserProfile, data.image, 15f, 1f)
                4 -> imageLoader.loadGreyScaleImage(ivUserProfile, data.image)
                else -> imageLoader.loadImage(ivUserProfile, data.image)
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
