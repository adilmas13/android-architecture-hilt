package com.androidarchitecture.domain.repository

import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.models.User

interface UserRepository {
    suspend fun getUserDetail(id: Int): Result<User>
    suspend fun getUsers(): Result<List<User>>
}
