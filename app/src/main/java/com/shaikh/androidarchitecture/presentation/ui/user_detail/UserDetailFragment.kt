package com.shaikh.androidarchitecture.presentation.ui.user_detail

import android.os.Bundle
import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.presentation.base.BaseFragment
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator

class UserDetailFragment : BaseFragment<UserDetailViewModel>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getLayoutId() = R.layout.user_detail_fragment

    override fun setObservers() {

    }

    override fun createViewModel() = ViewModelCreator(UserDetailViewModel::class.java)


}
