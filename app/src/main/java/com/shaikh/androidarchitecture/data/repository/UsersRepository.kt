package com.shaikh.androidarchitecture.data.repository

import com.shaikh.androidarchitecture.data.repository.base.BaseRepository
import com.shaikh.androidarchitecture.domain.entities.Result
import com.shaikh.androidarchitecture.domain.entities.Users

class UsersRepository : BaseRepository() {

    suspend fun getUsers(): Result<List<Users>> {
        return parseResult(api.getUsers()) { response -> response.users.map { it.toUser() } }
    }

    suspend fun getUserDetail(id: Int): Result<Users> {
        return parseResult(api.getUserDetail(id)) { it.data.toUser() }
    }
}
