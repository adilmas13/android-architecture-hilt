package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class UserDetailUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCase<User>() {

    private var id by Delegates.notNull<Int>()

    override suspend fun makeRequest() = repository.getUserDetail(id)

    fun getUserDetail(id: Int, success: (User) -> Unit, failure: (Exception) -> Unit) {
        this.id = id
        execute(success, failure)
    }
}
