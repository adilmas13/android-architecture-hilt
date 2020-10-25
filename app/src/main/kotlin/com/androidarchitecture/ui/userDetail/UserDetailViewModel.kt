package com.androidarchitecture.ui.userDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.usecase.UserDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserDetailViewModel @ViewModelInject constructor(
    private val useDetailUseCase: UserDetailUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val user = savedStateHandle.get<User>("user")

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    @ExperimentalCoroutinesApi
    fun getUserDetails() {
        if (user == null) return
        viewModelScope.launch {
            useDetailUseCase.getUserDetail(user.id)
                .onStart { _loading.value = true }
                .onCompletion { _loading.value = false }
                .catch { _error.value = it.message } // on error
                .collect { _userData.value = it } // on success
        }
    }
}
