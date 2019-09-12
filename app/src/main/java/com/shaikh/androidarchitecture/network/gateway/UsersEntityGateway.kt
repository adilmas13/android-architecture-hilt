package com.shaikh.androidarchitecture.network.gateway

import com.shaikh.androidarchitecture.Result
import com.shaikh.androidarchitecture.Users

class UsersEntityGateway : BaseEntityGateway() {

    suspend fun getUsers(): Result<List<Users>> {
        val demo = api.getUsers()
        return splitBodyAndError(demo) { it ->
            it.users.map { Users("${it.first_name} ${it.last_name}", it.avatar) }
        }
    }
}
