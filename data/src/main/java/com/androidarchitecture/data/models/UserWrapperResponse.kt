package com.androidarchitecture.data.models

import com.google.gson.annotations.SerializedName

data class UserWrapperResponse(
    @SerializedName("data") val data: UsersResponse
)
