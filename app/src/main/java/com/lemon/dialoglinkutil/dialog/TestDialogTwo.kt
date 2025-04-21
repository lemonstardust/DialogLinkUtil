package com.lemon.dialoglinkutil.dialog

import androidx.fragment.app.FragmentActivity
import com.lemon.dialoglink.base.DialogComponent
import com.lemon.dialoglinkutil.mock.MockApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestDialogTwo : DialogComponent {


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

    override suspend fun isIntercept() = withContext(Dispatchers.IO) {
        if (timeLimit()) {
            return@withContext false
        }
        val apiResult = apiGetString()
        return@withContext (apiResult.length > 4)
    }


    private suspend fun apiGetString() = withContext(Dispatchers.IO) {
        val apiResult = MockApi.getStringApi("test1111")
        apiResult
    }

    private fun timeLimit(): Boolean {
        return System.currentTimeMillis() - mLastShowTime < 2 * 60 * 1000L
    }
}