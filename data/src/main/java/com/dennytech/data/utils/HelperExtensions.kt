package com.dennytech.data.utils

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.resolveError(): String {

    return when (this) {
        is SocketTimeoutException -> {
            "Request timeout, Please try again"
        }
        is ConnectException -> {
            "No internet access"
        }
        is UnknownHostException -> {
            "No internet access"
        }

        is IOException -> {
            "No internet access"
        }

        is HttpException -> {
            try {
//                this.response()?.message() ?: "Error"
                this.response()?.errorBody()?.charStream()?.let {
                    Gson().fromJson(it, Map::class.java)["error_description"]
                }.toString()
            } catch (ex: Exception) {
                "Connection failed"
            }
        }

        else -> {
            "Error occurred"
        }
    }
}