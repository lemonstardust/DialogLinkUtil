package com.lemon.dialoglinkutil.dialog.base

import android.content.Context
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.lemon.dialoglinkutil.dialog.TestDialogOne
import com.lemon.dialoglinkutil.dialog.TestDialogTwo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object DialogManager {

    private val TAG = "DialogManager"

    private val showingDialogs = mutableListOf<DialogComponent>()

    private val preDialogs = mutableListOf<DialogComponent>()

    private lateinit var mContext: WeakReference<Context>

    fun init(context: Context) {

        mContext = WeakReference(context)
//        addComponent(TestDialogOne())
//        addComponent(TestDialogTwo())
    }

    fun getContext(): Context {
        return mContext.get()!!
    }

    fun addComponent(component: DialogComponent) {
        preDialogs.add(component)
        sortDialogs()
    }

    private fun sortDialogs() {
        preDialogs.sortByDescending { it.priorityAndTag().first }
        Log.i(TAG, "sortDialogs: $preDialogs")
    }

    fun onDialogShow(component: DialogComponent) {
        showingDialogs.add(component)
    }

    fun onDialogDismiss(component: DialogComponent) {
        showingDialogs.remove(component)
    }

    fun checkCanShow() {
        val showingTag = showingDialogs.getOrNull(0)?.priorityAndTag()?.second
        if (showingTag.isNullOrBlank().not()) {
            val fragment =
                (getContext() as FragmentActivity).supportFragmentManager.findFragmentByTag(
                    showingTag
                )
            if (fragment != null) {
                Log.e(TAG, "onDialogShow: $showingTag")
                return
            } else {
                showingDialogs.removeIf { it.priorityAndTag().second == showingTag }
            }
        }

        CoroutineScope(Dispatchers.Default).launch {

            val dialog = onGetNextDialog()
            if (dialog != null) {
                dialog.showDialog()
            } else {
                Log.e(TAG, "没有可以显示的对话框")
            }
        }
    }


    private suspend fun onGetNextDialog() = suspendCoroutine<DialogComponent?> { result ->
        if (showingDialogs.isNotEmpty()) {
            result.resume(null)
        } else {
            CoroutineScope(Dispatchers.Default).launch {
                for (dialog in preDialogs) {
                    val task = async {
                        dialog.isIntercept()
                    }
                    Log.i(
                        TAG,
                        "startGet:time ${System.currentTimeMillis() / 1000} ${dialog.priorityAndTag().second}}"
                    )
                    val hasNextDialog = task.await()
                    Log.i(
                        TAG,
                        "endGet:time ${System.currentTimeMillis() / 1000} ${dialog.priorityAndTag().second}}"
                    )
                    if (hasNextDialog) {
                        result.resume(dialog)
//                    return@launch
                        return@launch
//                        break

                    }
                }
                Log.i(
                    TAG,
                    "onGetNextDialog:time ${System.currentTimeMillis() / 1000} NoDialog"
                )
                result.resume(null)
            }
        }
    }
}

// 定义一个同时实现两个接口的类型
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
//        it.resume(false)
    }
}