package com.lemon.dialoglinkutil.testITask

import com.lemon.dialoglinkutil.testITask.data.MockTaskBike
import com.lemon.itask.ITask

class Task2 : ITask<MockTaskBike?> {
    override suspend fun onTask(): MockTaskBike? {
        return getDataFromApi()
    }

    override fun getTaskTag(): Pair<Int, String> {
        return Pair(2, "Task2")
    }


    private fun getDataFromApi(): MockTaskBike? {
        // 模拟网络请求
        Thread.sleep(2000)
        return MockTaskBike("Task2", 2, 1)
    }
}