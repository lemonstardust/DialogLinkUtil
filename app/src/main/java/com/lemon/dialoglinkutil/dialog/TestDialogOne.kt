package com.lemon.dialoglinkutil.dialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.lemon.dialoglinkutil.data.UserInfo
import com.lemon.dialoglinkutil.dialog.base.DialogComponent
import com.lemon.dialoglinkutil.mock.MockApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TestDialogOne : DialogComponent {


    private var mUserInfo: UserInfo? = null

    override fun proceed() {

    }

    override fun showDialog() {
        super.showDialog()
        when (mUserInfo?.age) {
            in 0..18 -> {
                val fragmentManager = (getContext() as FragmentActivity).supportFragmentManager
                FunctionDialogOne.show(
                    fragmentManager,
                    "age below 18",
                    "${mUserInfo?.name};${mUserInfo?.age};${mUserInfo?.sex}"
                )
            }

            else -> {
                val fragmentManager = (getContext() as FragmentActivity).supportFragmentManager
                FunctionDialogOne.show(
                    fragmentManager,
                    "age beyond 18",
                    "${mUserInfo?.name};${mUserInfo?.age};${mUserInfo?.sex}"
                )
            }
        }
    }

    override fun onDismiss() {
        super.onDismiss()
    }


    override suspend fun isIntercept() = suspendCoroutine {
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiGetUserInfo("name1", 20, 1)
            delay(200)
            it.resume(result.age > 18)
        }

    }



    override fun priorityAndTag(): Pair<Int, String> {
        return Pair(1, "FunctionDialogOne")
    }

    private suspend fun apiGetUserInfo(name: String, age: Int, sex: Int) =
        suspendCoroutine { result ->
            CoroutineScope(Dispatchers.IO).launch {
                val apiResult = MockApi.getUserInfo(name, age, sex)
                result.resume(apiResult)
            }

        }
}