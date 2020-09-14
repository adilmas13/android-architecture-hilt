package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersListUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<List<User>>() {

    override suspend fun makeRequest(): Flow<List<User>> {
        return repository.getUsers()
    }

    suspend fun getUsers(): Flow<List<User>> {
        return execute()
    }
}
