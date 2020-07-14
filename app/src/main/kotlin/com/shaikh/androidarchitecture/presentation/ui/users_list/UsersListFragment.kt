package com.shaikh.androidarchitecture.presentation.ui.users_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.presentation.base.BaseFragment
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator
import com.shaikh.androidarchitecture.presentation.ui.OnUserClickListener
import com.shaikh.androidarchitecture.presentation.ui.UsersListAdapter
import kotlinx.android.synthetic.main.users_list_fragment.loader
import kotlinx.android.synthetic.main.users_list_fragment.rvUsers

class UsersListFragment : BaseFragment<UsersListViewModel>(), OnUserClickListener {

    override fun onUserClicked(id: Int) {
        val action = UsersListFragmentDirections.showUserDetails(id)
        findNavController().navigate(action)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUsers()
    }

    override fun getLayoutId() = R.layout.users_list_fragment

    override fun subscribeToObservers() {
        viewModel.loading.observe(this, Observer { handleLoaderVisibility(it) })
        viewModel.data.observe(this, Observer { rvUsers.adapter = UsersListAdapter(it, this) })
        viewModel.errorMessage.observe(this, Observer { showMessage(it) })
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = ViewModelCreator(UsersListViewModel::class.java)
}
