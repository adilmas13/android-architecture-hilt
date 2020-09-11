package com.androidarchitecture.ui.userDetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.utilities.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_detail_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<UserDetailViewModel>() {

    @Inject
    lateinit var imageLoader: ImageLoader

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

    private fun showDetails(it: User) {
        imageLoader.loadImage(ivUserProfile, it.image)
        tvUsername.text = it.name
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun createViewModel() = UserDetailViewModel::class.java
}
