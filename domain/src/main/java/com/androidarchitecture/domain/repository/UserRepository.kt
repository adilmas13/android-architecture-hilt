package com.androidarchitecture.domain.repository

import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.models.Users

interface UserRepository {
    suspend fun getUserDetail(id: Int): Result<Users>
    suspend fun getUsers(): Result<List<Users>>
}