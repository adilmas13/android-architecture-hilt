package com.shaikh.androidarchitecture.presentation.ui.user_detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.presentation.model.Users
import com.shaikh.androidarchitecture.presentation.base.BaseFragment
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator
import com.shaikh.androidarchitecture.presentation.utilities.Imagify
import kotlinx.android.synthetic.main.user_detail_fragment.*

class UserDetailFragment : BaseFragment<UserDetailViewModel>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUserDetails()
    }

    override fun getLayoutId() = R.layout.user_detail_fragment

    override fun subscribeToObservers() {
        viewModel.apply {
            loading.observe(this@UserDetailFragment, Observer { handleLoaderVisibility(it) })
            error.observe(this@UserDetailFragment, Observer { showMessage(it) })
            userData.observe(this@UserDetailFragment, Observer { showDetails(it) })
        }
    }

    private fun showDetails(it: Users) {
        Imagify.loadImage(ivUserProfile, it.image)
        tvUsername.text = it.name
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel(): ViewModelCreator<UserDetailViewModel> {
        arguments?.let {
            val args = UserDetailFragmentArgs.fromBundle(it)
            return ViewModelCreator(
                UserDetailViewModel::class.java,
                UserDetailViewModelFactory(args.userId)
            )
        }
        return ViewModelCreator(UserDetailViewModel::class.java)
    }


}
