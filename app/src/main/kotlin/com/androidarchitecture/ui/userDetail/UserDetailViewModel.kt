package com.androidarchitecture.ui.userDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.usecase.UserDetailUseCase

class UserDetailViewModel @ViewModelInject constructor(
    private val useDetailUseCase: UserDetailUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId = savedStateHandle.get<Int>("userId") ?: -1

    val loading = MutableLiveData<Boolean>()

    val userData = MutableLiveData<Users>()

    val error = MutableLiveData<String>()

    fun getUserDetails() {
        loading.value = true
        useDetailUseCase.getUserDetail(userId, ::onApiSuccess, ::onApiFailure)
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
