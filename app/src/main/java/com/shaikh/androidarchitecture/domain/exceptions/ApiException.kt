package com.shaikh.androidarchitecture.domain.exceptions

import java.lang.Exception

class ApiException(
        error: String?
) : Exception(error)