package com.shaikh.androidarchitecture.presentation.ui.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class UserDetailViewModelFactory(private val userId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
