package com.shaikh.androidarchitecture.domain.entities

import com.google.gson.annotations.SerializedName

data class UserWrapperResponse(
    @SerializedName("data") val data: UsersResponse
)