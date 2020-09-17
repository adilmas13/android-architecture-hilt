package com.androidarchitecture.ui.userDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val loading = MutableLiveData<Boolean>()

    val userData = MutableLiveData<User>()

    val error = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun getUserDetails() {
        if (user == null) return
        viewModelScope.launch {
            useDetailUseCase.getUserDetail(user.id)
                .onStart { loading.value = true }
                .onCompletion { loading.value = false }
                .catch { error.value = it.message } // on error
                .collect { userData.value = it } // on success
        }
    }
}
