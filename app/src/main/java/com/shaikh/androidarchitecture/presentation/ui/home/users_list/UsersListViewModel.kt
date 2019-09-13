package com.shaikh.androidarchitecture.presentation.ui.home.users_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaikh.androidarchitecture.domain.entities.Users
import com.shaikh.androidarchitecture.domain.usecase.UsersListUseCase

class UsersListViewModel : ViewModel() {
    var data: MutableLiveData<MutableList<Users>> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    private val usersUseCase = UsersListUseCase()

    fun getUsers() {
        loading.value = true
        usersUseCase
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
