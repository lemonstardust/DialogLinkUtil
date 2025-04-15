package com.lemon.dialoglinkutil.dialog.base

import android.content.Context

interface IDialogLifecycles {

    fun getContext(): Context
    fun showDialog()

    fun onShow()

    fun onDismiss()


}