package com.androidarchitecture.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.androidarchitecture.model.ViewModelCreator
import com.androidarchitecture.utilities.showToast

abstract class BaseActivity<M : ViewModel> : AppCompatActivity() {

    private var toastInstance: Toast? = null

    lateinit var viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun subscribeToObservers()

    abstract fun createViewModel(): ViewModelCreator<M>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        generateViewModel()
        subscribeToObservers()
    }

    private fun generateViewModel() {
        val creator = createViewModel()
        viewModel = ViewModelProviders.of(this, creator.factory).get(creator.type)
    }

    fun showMessage(message: String) {
        toastInstance = showToast(message)
    }

    override fun onDestroy() {
        toastInstance?.cancel() // cancel and toast message that is being displayed
        super.onDestroy()
    }
}
