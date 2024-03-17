package com.app.ticketmaster.network

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null,
    val stringRes: Int? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, code: Int? = null, stringRes: Int? = null, data: T? = null) :
        Resource<T>(data, message, code, stringRes)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}
