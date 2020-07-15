package com.androidarchitecture.ui.usersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.usecase.UsersListUseCase

class UsersListViewModel(private val usersListUseCase: UsersListUseCase) : ViewModel() {
    var data: MutableLiveData<MutableList<Users>> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getUsers() {
        loading.value = true
        usersListUseCase
            .getUsers(this::onApiSuccess, this::onApiFailure)
    }

    private fun onApiSuccess(users: List<Users>) {
        data.value = users.toMutableList()
        loading.value = false
    }

    private fun onApiFailure(error: Exception) {
        loading.value = false
        errorMessage.value = error.message
    }
}
