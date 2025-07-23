package com.lemon.itask

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskLogic {

    private val TAG = "TaskLogic"
    private val taskList = mutableListOf<ITask<*>>()

    private val listeners = mutableListOf<ITaskListener>()


    fun addTask(task: ITask<*>) {
        if (taskList.contains(task)) {
            return
        }

        taskList.add(task)
        taskList.sortByDescending { it.getTaskTag().first }
    }

    fun addListener(listener: ITaskListener) {
        if (listeners.contains(listener)) {
            return
        }
        listeners.add(listener)
    }

    fun removeListener(listener: ITaskListener) {
        listeners.remove(listener)
    }

    fun startTask() {
        if (taskList.isEmpty()) {
            return
        }
        CoroutineScope(Dispatchers.Default).launch {

            for (task in taskList) {

                val data = async { task.onTask() }.await()

                if (data != null) {
                    // 有数据，返回
                    Log.i(TAG, "startTask: ${task.getTaskTag().second}, data: $data")
                    listeners.forEach {
                        withContext(it.onDispatch()) {
                            it.onReceiveTask(data)
                        }
                    }
                    return@launch
                } else {
                    // 没有数据，继续下一个任务
                    Log.i(TAG, "startTask: ${task.getTaskTag().second}, data is null")
                }

            }

        }
    }

}