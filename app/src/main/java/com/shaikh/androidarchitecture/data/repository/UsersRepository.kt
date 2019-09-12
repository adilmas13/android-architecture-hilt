package com.shaikh.androidarchitecture.data.repository

import com.shaikh.androidarchitecture.domain.entities.Result
import com.shaikh.androidarchitecture.domain.entities.Users
import com.shaikh.androidarchitecture.data.repository.base.BaseRepository

class UsersRepository : BaseRepository() {

    suspend fun getUsers(): Result<List<Users>> {
        return parseResult(api.getUsers()) { response -> response.users.map { it.toUser() } }
    }
}
