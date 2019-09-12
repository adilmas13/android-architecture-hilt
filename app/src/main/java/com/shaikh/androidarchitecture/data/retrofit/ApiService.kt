package com.shaikh.androidarchitecture.data.retrofit

import com.shaikh.androidarchitecture.domain.entities.UsersWrapperResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/users?page=1")
    suspend fun getUsers(): Response<UsersWrapperResponse>

}