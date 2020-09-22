package com.androidarchitecture.domain.models

import java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val image: String,
    val email: String
) : Serializable
