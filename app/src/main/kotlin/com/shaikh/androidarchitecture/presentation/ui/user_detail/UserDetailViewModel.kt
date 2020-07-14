package com.shaikh.androidarchitecture.presentation.ui.user_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaikh.androidarchitecture.presentation.model.Users
import com.shaikh.androidarchitecture.domain.usecase.UserDetailUseCase

class UserDetailViewModel(private val userId: Int) : ViewModel() {

    private val useCase = UserDetailUseCase()

    val loading = MutableLiveData<Boolean>()

    val userData = MutableLiveData<Users>()

    val error = MutableLiveData<String>()

    fun getUserDetails() {
        loading.value = true
        useCase.getUserDetail(userId, ::onApiSuccess, this::onApiFailure)
    }

    private fun onApiSuccess(user: Users) {
        loading.value = false
        userData.value = user
    }

    private fun onApiFailure(exception: Exception) {
        loading.value = false
        error.value = exception.message
    }
}
