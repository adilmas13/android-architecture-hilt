package com.shaikh.androidarchitecture.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.presentation.base.BaseActivity
import com.shaikh.androidarchitecture.presentation.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = MainActivityViewModel::class.java

    override fun setObservers() {
        viewModel.loading.observe(this, Observer { handleLoaderVisibility(it) })
        viewModel.data.observe(this, Observer { text.text = it[0].name })
    }

    private fun handleLoaderVisibility(isVisible: Boolean) {
        loader.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
    }
}
