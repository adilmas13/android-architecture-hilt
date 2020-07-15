package com.androidarchitecture.data.models

import com.google.gson.annotations.SerializedName

// TODO : change this data structure based in the error response being sent by the api team
data class ApiResponseWrapper<out T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: T? = null,
    @SerializedName("error") val error: String = "",
    @SerializedName("message") val message: String = ""
)
