package com.androidarchitecture.ui.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidarchitecture.domain.usecase.UserDetailUseCase

@Suppress("UNCHECKED_CAST")
class UserDetailViewModelFactory(
    private val userId: Int,
    private val useDetailUseCase: UserDetailUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(userId, useDetailUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
