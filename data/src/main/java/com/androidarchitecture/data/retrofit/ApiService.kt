package com.androidarchitecture.data.retrofit

import com.androidarchitecture.data.entities.UserWrapperEntity
import com.androidarchitecture.data.entities.UsersWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val GET_USERS = "api/users?page=1"
        const val GET_USER = "api/users"
    }

    @GET(GET_USERS)
    suspend fun getUsers(): Response<UsersWrapperResponse>

    @GET("$GET_USER/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): Response<UserWrapperEntity>
}
