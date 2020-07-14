package com.shaikh.androidarchitecture.domain.usecase

import com.shaikh.androidarchitecture.data.repository.UsersRepository
import com.shaikh.androidarchitecture.domain.base.UseCase
import com.shaikh.androidarchitecture.domain.entities.Result
import com.shaikh.androidarchitecture.presentation.model.Users
import java.lang.Exception

class UsersListUseCase : UseCase<List<Users>>() {

    private val entity = UsersRepository()

    override suspend fun makeRequest(): Result<List<Users>> {
        return entity.getUsers()
    }

    fun getUsers(success: (List<Users>) -> Unit, failure: (Exception) -> Unit) {
        execute(success, failure)
    }
}
