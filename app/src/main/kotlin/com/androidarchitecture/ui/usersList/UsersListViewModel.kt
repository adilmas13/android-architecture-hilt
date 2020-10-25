package com.androidarchitecture.ui.usersList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.usecase.UsersListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UsersListViewModel @ViewModelInject constructor(
    private val usersListUseCase: UsersListUseCase
) : ViewModel() {

    private val _data: MutableLiveData<MutableList<User>> = MutableLiveData()
    val data: LiveData<MutableList<User>> = _data

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    @ExperimentalCoroutinesApi
    fun getUsers() {
        viewModelScope.launch {
            usersListUseCase
                .getUsers()
                .onStart { _loading.value = true }
                .onCompletion { _loading.value = false }
                .catch { _errorMessage.value = it.message } // on error
                .collect { _data.value = it.toMutableList() } // on success
        }
    }
}
