package com.shaikh.androidarchitecture.presentation.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.domain.entities.Users
import com.shaikh.androidarchitecture.presentation.utilities.inflate
import kotlinx.android.synthetic.main.adapter_user.view.*

class UsersListAdapter(val list: List<Users>) :
    RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersListViewHolder(parent.inflate(R.layout.adapter_user))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.apply {
            tvUsername.text = data.name
            ivUserProfile.load(data.image) {
                crossfade(1000)
                transformations(RoundedCornersTransformation(20f))
            }
        }
    }

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}