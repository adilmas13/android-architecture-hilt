package com.androidarchitecture.ui.home

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.theme -> toggleTheme()
        else -> super.onOptionsItemSelected(item)
    }


    private fun toggleTheme(): Boolean {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        return true
    }

    override fun createViewModel() = MainActivityViewModel::class.java

    override fun getLayoutId() = R.layout.activity_main

    override fun subscribeToObservers() = Unit
}
