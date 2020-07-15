package com.androidarchitecture.ui.userDetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseFragment
import com.androidarchitecture.data.repository.RestApiUsersRepository
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.usecase.UserDetailUseCase
import com.androidarchitecture.model.ViewModelCreator
import com.androidarchitecture.utilities.Imagify
import com.androidarchitecture.utilities.LiveConnectivityMonitor
import kotlinx.android.synthetic.main.user_detail_fragment.ivUserProfile
import kotlinx.android.synthetic.main.user_detail_fragment.loader
import kotlinx.android.synthetic.main.user_detail_fragment.tvUsername

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
                UserDetailViewModelFactory(
                    args.userId,
                    UserDetailUseCase(RestApiUsersRepository(LiveConnectivityMonitor()))
                )
            )
        }
        return ViewModelCreator(UserDetailViewModel::class.java)
    }
}
