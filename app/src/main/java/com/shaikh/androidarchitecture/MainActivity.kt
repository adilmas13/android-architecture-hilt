package com.shaikh.androidarchitecture

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = MainActivityViewModel::class.java

    override fun setObservers() {
    }

    private fun setBaba(it: Boolean) {

    }

}
