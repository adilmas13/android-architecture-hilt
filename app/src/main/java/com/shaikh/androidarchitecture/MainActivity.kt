package com.shaikh.androidarchitecture

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = MainActivityViewModel::class.java

    override fun setObservers() {
        viewModel.loading.observe(this, Observer { handleLoaderVisibility(it) })
        viewModel.data.observe(this, Observer { text.text = it[0].name })
    }

    private fun handleLoaderVisibility(it: Boolean) {
// TODO: implement loader
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
    }
}
