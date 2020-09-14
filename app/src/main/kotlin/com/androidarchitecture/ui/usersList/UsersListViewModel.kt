package com.androidarchitecture.ui.usersList

import androidx.hilt.lifecycle.ViewModelInject
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
    var data: MutableLiveData<MutableList<User>> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    @ExperimentalCoroutinesApi
    fun getUsers() {
        viewModelScope.launch {
            usersListUseCase
                .getUsers()
                .onStart { loading.value = true }
                .onCompletion { loading.value = false }
                .catch { errorMessage.value = it.message } // on error
                .collect { data.value = it.toMutableList() } // on success
        }
    }
}
