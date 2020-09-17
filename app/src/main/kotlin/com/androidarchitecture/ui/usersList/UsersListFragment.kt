package com.androidarchitecture.ui.usersList

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.ui.UsersListAdapter
import com.androidarchitecture.utilities.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.users_list_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class UsersListFragment : BaseFragment<UsersListViewModel>() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        viewModel.getUsers()
    }

    private fun initViews() {
        rvUsers.adapter = UsersListAdapter(imageLoader) { user ->
            val action = UsersListFragmentDirections.showUserDetails(user)
            findNavController().navigate(action)
        }
    }

    override fun getLayoutId() = R.layout.users_list_fragment

    override fun subscribeToObservers() {
        viewModel.loading.observe(this, { handleLoaderVisibility(it) })
        viewModel.data.observe(this, { (rvUsers.adapter as UsersListAdapter).submitList(it) })
        viewModel.errorMessage.observe(this, { showMessage(it) })
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = UsersListViewModel::class.java
}
