package com.shaikh.androidarchitecture.presentation.ui.home

import com.shaikh.androidarchitecture.R
import com.shaikh.androidarchitecture.presentation.base.BaseActivity
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun createViewModel() =
        ViewModelCreator(MainActivityViewModel::class.java)

    override fun getLayoutId() = R.layout.activity_main

    override fun subscribeToObservers() {
    }
}
