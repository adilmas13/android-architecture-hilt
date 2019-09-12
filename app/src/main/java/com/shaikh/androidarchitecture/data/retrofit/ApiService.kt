package com.shaikh.androidarchitecture.data.retrofit

import com.shaikh.androidarchitecture.domain.entities.UsersWrapperResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val GET_USERS = "api/users?page=1"
    }

    @GET(GET_USERS)
    suspend fun getUsers(): Response<UsersWrapperResponse>

}