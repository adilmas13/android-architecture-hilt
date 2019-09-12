package com.shaikh.androidarchitecture

import java.lang.Exception

class ApiException(
        error: String?
) : Exception(error)