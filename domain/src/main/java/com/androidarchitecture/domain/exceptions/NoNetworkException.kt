package com.androidarchitecture.domain.exceptions

import java.io.IOException

class NoNetworkException : IOException() {

    override val message: String?
        get() = "No Internet Connection. Please Try Again"
}
