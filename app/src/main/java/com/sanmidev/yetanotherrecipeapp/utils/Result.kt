package com.sanmidev.yetanotherrecipeapp.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    sealed class Error(val exceptionId: Int) : Result<Nothing>() {
        class RecoverableError(messageId: Int) : Error(messageId)
        class NonRecoverableError(messageId: Int) :
            Error(messageId)
    }

    object InProgress : Result<Nothing>()
}