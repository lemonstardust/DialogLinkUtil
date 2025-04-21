package com.lemon.dialoglink.base

import android.content.Context

interface DialogComponent : IDialogLifecycles, IDialogInterceptor {

    override fun getContext(): Context {
        return DialogManager.getContext()
    }

    override fun showDialog() {
        onShow()
    }

    override fun onShow() {
        DialogManager.onDialogShow(this)
    }

    override fun onDismiss() {
        DialogManager.onDialogDismiss(this)
    }

    override suspend fun isIntercept(): Boolean {
        return false
    }
}