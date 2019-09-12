package com.shaikh.androidarchitecture

import com.shaikh.androidarchitecture.network.gateway.UsersEntityGateway
import java.lang.Exception

class UsersRepository : BaseApiRepository<List<Users>>() {

    private val entity = UsersEntityGateway()

    override suspend fun makeRequest(): Result<List<Users>> {
        return entity.getUsers()
    }

    fun getUsers(success: (List<Users>) -> Unit, failure: (Exception) -> Unit) {
        execute(success, failure)
    }


}