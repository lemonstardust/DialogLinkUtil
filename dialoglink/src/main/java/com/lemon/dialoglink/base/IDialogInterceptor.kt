package com.lemon.dialoglink.base

interface IDialogInterceptor {

    suspend fun isIntercept(): Boolean

    fun priorityAndTag(): Pair<Int, String>
}