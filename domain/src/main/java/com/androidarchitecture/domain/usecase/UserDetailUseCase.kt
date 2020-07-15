package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.Users
import com.androidarchitecture.domain.repository.UserRepository
import kotlin.properties.Delegates

class UserDetailUseCase(private val repository: UserRepository) : UseCase<Users>() {

    private var id by Delegates.notNull<Int>()

    override suspend fun makeRequest() = repository.getUserDetail(id)

    fun getUserDetail(id: Int, success: (Users) -> Unit, failure: (Exception) -> Unit) {
        this.id = id
        execute(success, failure)
    }
}
