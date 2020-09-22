package com.androidarchitecture.data.entities

import com.androidarchitecture.domain.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersWrapperResponse(
    @SerialName("data") val users: List<UsersResponse>
)

@Serializable
data class UsersResponse(
    @SerialName("id") val id: Int,
    @SerialName("email") val email: String,
    @SerialName("first_name") val first_name: String,
    @SerialName("last_name") val last_name: String,
    @SerialName("avatar") val avatar: String
) {
    fun toUser() = User(
        id,
        "$first_name $last_name",
        avatar,
        email
    )
}
