package com.shaikh.androidarchitecture.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.shaikh.androidarchitecture.presentation.ViewModelCreator

abstract class BaseActivity<M : ViewModel> : AppCompatActivity() {

    lateinit var viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setObservers()

    abstract fun createViewModel(): ViewModelCreator<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        generateViewModel()
        setObservers()
    }

    private fun generateViewModel() {
        val creator = createViewModel()
        viewModel = ViewModelProviders.of(this, creator.factory).get(creator.type)
    }

    fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}
