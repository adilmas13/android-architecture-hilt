package com.androidarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidarchitecture.utilities.showToast

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment<M : ViewModel> : Fragment() {

    private var toastInstance: Toast? = null

    lateinit var viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun subscribeToObservers()

    abstract fun createViewModel(): Class<M>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
