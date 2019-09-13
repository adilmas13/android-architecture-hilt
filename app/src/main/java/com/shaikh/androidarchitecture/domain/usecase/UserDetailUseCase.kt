package com.shaikh.androidarchitecture.domain.usecase

import com.shaikh.androidarchitecture.data.repository.UsersRepository
import com.shaikh.androidarchitecture.domain.base.UseCase
import com.shaikh.androidarchitecture.domain.entities.Result
import com.shaikh.androidarchitecture.domain.entities.Users
import kotlin.properties.Delegates

class UserDetailUseCase : UseCase<Users>() {

    private val entity = UsersRepository()

    private var id by Delegates.notNull<Int>()

    override suspend fun makeRequest(): Result<Users> {
        return entity.getUserDetail(id)
    }

    fun getUserDetail(id: Int, success: (Users) -> Unit, failure: (Exception) -> Unit) {
        this.id = id
        execute(success, failure)
    }


}