package com.shaikh.androidarchitecture.domain.entities

import com.google.gson.annotations.SerializedName

data class ApiResponseWrapper<out T>(
        @SerializedName("status") val status: Boolean,
        @SerializedName("data") val data: T?,
        @SerializedName("error") val error: String?,
        @SerializedName("message") val message: String?
)
