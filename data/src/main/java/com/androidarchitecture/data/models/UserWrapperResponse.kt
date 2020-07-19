package com.androidarchitecture.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserWrapperResponse(
    @SerialName("data") val data: UsersResponse
)
