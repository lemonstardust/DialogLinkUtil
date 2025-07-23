package com.lemon.itask

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface ITaskListener {

    fun onReceiveTask(data: Any?)

    fun onDispatch(): CoroutineDispatcher
}
