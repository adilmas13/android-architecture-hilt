package com.androidarchitecture.domain.exceptions

class ApiException(
    error: String?
) : Exception(error)
