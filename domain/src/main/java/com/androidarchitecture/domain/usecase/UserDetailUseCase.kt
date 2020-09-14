package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.User
import com.androidarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.properties.Delegates

class UserDetailUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCase<User>() {

    private var id by Delegates.notNull<Int>()

    override suspend fun makeRequest() = repository.getUserDetail(id)

    suspend fun getUserDetail(id: Int): Flow<User> {
        this.id = id
        return execute()
    }
}
