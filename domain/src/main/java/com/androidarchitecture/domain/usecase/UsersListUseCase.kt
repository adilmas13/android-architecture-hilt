package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.repository.UserRepository
import javax.inject.Inject

class UsersListUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<List<User>>() {

    override suspend fun makeRequest(): Result<List<User>> {
        return repository.getUsers()
    }

    fun getUsers(success: (List<User>) -> Unit, failure: (Exception) -> Unit) {
        execute(success, failure)
    }
}
