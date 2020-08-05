package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.repository.UserRepository
import javax.inject.Inject

class UsersListUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<List<Users>>() {

    override suspend fun makeRequest(): Result<List<Users>> {
        return repository.getUsers()
    }

    fun getUsers(success: (List<Users>) -> Unit, failure: (Exception) -> Unit) {
        execute(success, failure)
    }
}
