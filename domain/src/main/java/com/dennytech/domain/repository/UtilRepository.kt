package com.dennytech.domain.repository

interface UtilRepository {
    fun getNetworkError(throwable: Throwable): String
    fun currentDate(): String
}