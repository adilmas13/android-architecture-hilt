package com.androidarchitecture.ui.usersList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.usecase.UsersListUseCase

class UsersListViewModel @ViewModelInject constructor(
    private val usersListUseCase: UsersListUseCase
) : ViewModel() {
    var data: MutableLiveData<MutableList<User>> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getUsers() {
        loading.value = true
        usersListUseCase
            .getUsers(this::onApiSuccess, this::onApiFailure)
    }

    private fun onApiSuccess(users: List<User>) {
        data.value = users.toMutableList()
        loading.value = false
    }

    private fun onApiFailure(error: Exception) {
        loading.value = false
        errorMessage.value = error.message
    }
}
