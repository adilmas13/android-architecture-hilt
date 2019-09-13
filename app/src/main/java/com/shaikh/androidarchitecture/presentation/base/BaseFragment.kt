package com.shaikh.androidarchitecture.presentation.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.shaikh.androidarchitecture.presentation.model.ViewModelCreator

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment<M : ViewModel> : Fragment() {

    lateinit var viewModel: M

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setObservers()

    abstract fun createViewModel(): ViewModelCreator<M>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        generateViewModel()
        setObservers()
    }

    private fun generateViewModel() {
        val creator = createViewModel()
        viewModel = ViewModelProviders.of(this, creator.factory).get(creator.type)
    }

}
