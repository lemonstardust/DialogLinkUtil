package com.lemon.dialoglinkutil.dialog.base

interface IDialogInterceptor {

    suspend fun isIntercept(): Boolean

    fun proceed()

    fun priorityAndTag(): Pair<Int, String>
}