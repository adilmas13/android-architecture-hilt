package com.androidarchitecture.data.repository

import com.androidarchitecture.data.repository.base.BaseRestApiRepository
import com.androidarchitecture.domain.NetworkMonitor
import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.repository.UserRepository

class RestApiUsersRepository(networkMonitor: NetworkMonitor) : UserRepository,
    BaseRestApiRepository(networkMonitor) {

    override suspend fun getUsers(): Result<List<Users>> {
        return parseResult(api.getUsers()) { response -> response.users.map { it.toUser() } }
    }

    override suspend fun getUserDetail(id: Int): Result<Users> {
        return parseResult(api.getUserDetail(id)) { it.data.toUser() }
    }
}
