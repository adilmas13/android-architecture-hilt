package com.shaikh.androidarchitecture.presentation.ui.home.users_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.presentation.base.BaseFragment
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator
import com.shaikh.androidarchitecture.presentation.ui.UsersListAdapter
import kotlinx.android.synthetic.main.users_list_fragment.*


class UsersListFragment : BaseFragment<UsersListViewModel>() {

    companion object {
        fun newInstance() = UsersListFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUsers()
    }

    override fun getLayoutId() = R.layout.users_list_fragment

    override fun setObservers() {
        viewModel.loading.observe(this, Observer { handleLoaderVisibility(it) })
        viewModel.data.observe(this, Observer { rvUsers.adapter = UsersListAdapter(it) })
        viewModel.errorMessage.observe(this, Observer { })
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = ViewModelCreator(UsersListViewModel::class.java)
}
