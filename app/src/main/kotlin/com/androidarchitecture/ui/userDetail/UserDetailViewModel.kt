package com.androidarchitecture.ui.userDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.usecase.UserDetailUseCase

class UserDetailViewModel(
    private val userId: Int,
    private val useDetailUseCase: UserDetailUseCase
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    val userData = MutableLiveData<Users>()

    val error = MutableLiveData<String>()

    fun getUserDetails() {
        loading.value = true
        useDetailUseCase.getUserDetail(userId, ::onApiSuccess, this::onApiFailure)
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
