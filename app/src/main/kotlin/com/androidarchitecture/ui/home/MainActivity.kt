package com.androidarchitecture.ui.home

import com.androidarchitecture.R
import com.androidarchitecture.base.BaseActivity
import com.androidarchitecture.helpers.ViewModelCreator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun createViewModel() =
        ViewModelCreator(MainActivityViewModel::class.java)

    override fun getLayoutId() = R.layout.activity_main

    override fun subscribeToObservers() {
    }
}
