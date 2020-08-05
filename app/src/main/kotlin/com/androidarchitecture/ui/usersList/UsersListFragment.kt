package com.androidarchitecture.ui.usersList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.ui.OnUserClickListener
import com.androidarchitecture.ui.UsersListAdapter
import com.androidarchitecture.utilities.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.users_list_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class UsersListFragment : BaseFragment<UsersListViewModel>(), OnUserClickListener {

    @Inject
    lateinit var imageLoader: ImageLoader

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
        viewModel.data.observe(
            this,
            Observer { rvUsers.adapter = UsersListAdapter(it, imageLoader, this) }
        )
        viewModel.errorMessage.observe(this, Observer { showMessage(it) })
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = UsersListViewModel::class.java
}
