package com.androidarchitecture.domain.repository

import com.androidarchitecture.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserDetail(id: Int): Flow<User>
    suspend fun getUsers(): Flow<List<User>>
}
