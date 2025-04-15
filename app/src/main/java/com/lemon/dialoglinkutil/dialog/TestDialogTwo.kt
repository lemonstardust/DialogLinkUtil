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

    override fun showDialog() {
        super.showDialog()
        val fragmentManager = (getContext() as FragmentActivity).supportFragmentManager

        val dialog = FunctionDialogTwo().apply {
            show(fragmentManager, "TestDialogTwo")

            setTitle("request 1")
            setContent("content 1")
        }

    }

    override fun priorityAndTag(): Pair<Int, String> {
        return Pair(2, "TestDialogTwo")
    }

    override suspend fun isIntercept() = suspendCoroutine { result ->
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
}