package com.lemon.dialoglinkutil.dialog

import androidx.fragment.app.FragmentActivity
import com.lemon.dialoglinkutil.dialog.base.DialogComponent
import com.lemon.dialoglinkutil.mock.MockApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TestDialogTwo() : DialogComponent {

    override fun proceed() {

    }

    private var mLastShowTime = 0L

    override fun showDialog() {
        super.showDialog()
        val fragmentManager = (getContext() as FragmentActivity).supportFragmentManager

        FunctionDialogTwo.show(fragmentManager, "request 1", "content 1")
        mLastShowTime = System.currentTimeMillis()
    }

    override fun priorityAndTag(): Pair<Int, String> {
        return Pair(2, "FunctionDialogTwo")
    }

    override suspend fun isIntercept() = suspendCoroutine { result ->
        if (timeLimit()) {
            result.resume(false)
            return@suspendCoroutine
        }
        CoroutineScope(Dispatchers.IO).launch {
            val apiResult = apiGetString()
            result.resume(apiResult.length > 4)
        }
    }

    private suspend fun apiGetString() = suspendCoroutine { result ->
        CoroutineScope(Dispatchers.IO).launch {
            val apiResult = MockApi.getStringApi("test1111")
            result.resume(apiResult)
        }
    }

    private fun timeLimit(): Boolean {
        return System.currentTimeMillis() - mLastShowTime < 2 * 60 * 1000L
    }
}