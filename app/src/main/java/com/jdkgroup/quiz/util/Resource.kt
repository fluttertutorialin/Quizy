package com.jdkgroup.quiz.util

sealed class Resource<T>(val data: T? = null, val msg: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(msg: String? = null, data: T? = null): Resource<T>(data, msg)
}
