package com.shaikh.androidarchitecture.network.networkModel

import com.google.gson.annotations.SerializedName

data class UsersWrapperResponse(
    @SerializedName("data") val users: List<UsersResponse>
)

data class UsersResponse(
    @SerializedName("id") val name: Int,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("avatar") val avatar: String
)
