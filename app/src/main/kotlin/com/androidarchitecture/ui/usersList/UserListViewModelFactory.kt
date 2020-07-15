package com.androidarchitecture.ui.usersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidarchitecture.domain.usecase.UsersListUseCase

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(
    private val usersListUseCase: UsersListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            return UsersListViewModel(usersListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
