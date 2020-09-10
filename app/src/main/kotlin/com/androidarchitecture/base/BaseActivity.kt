package com.androidarchitecture.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidarchitecture.utilities.showToast

abstract class BaseActivity<M : ViewModel> : AppCompatActivity() {

    private var toastInstance: Toast? = null

    lateinit var viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun subscribeToObservers()

    abstract fun createViewModel(): Class<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        generateViewModel()
        subscribeToObservers()
    }

    private fun generateViewModel() {
        viewModel = ViewModelProvider(this).get(createViewModel())
    }

    fun showMessage(message: String) {
        toastInstance = showToast(message)
    }

    override fun onDestroy() {
        toastInstance?.cancel() // cancel and toast message that is being displayed
        super.onDestroy()
    }
}
