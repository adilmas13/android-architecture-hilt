package com.shaikh.androidarchitecture.network

import com.shaikh.androidarchitecture.network.networkModel.UsersWrapperResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/users?page=1")
    suspend fun getUsers(): Response<UsersWrapperResponse>

}