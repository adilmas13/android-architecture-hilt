package com.androidarchitecture.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserWrapperEntity(
    @SerialName("data") val data: UsersResponse
)
