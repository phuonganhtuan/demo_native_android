package com.example.base

data class BaseResult<out T>(val status: LoadDataState, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T) =
            BaseResult(status = LoadDataState.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String) =
            BaseResult(status = LoadDataState.ERROR, data = data, message = message)

        fun <T> loading(data: T?) =
            BaseResult(status = LoadDataState.LOADING, data = data, message = null)
    }
}
