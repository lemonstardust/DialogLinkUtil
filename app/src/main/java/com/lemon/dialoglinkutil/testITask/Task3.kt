package com.lemon.dialoglinkutil.testITask

import com.lemon.dialoglinkutil.testITask.data.MockTaskBird
import com.lemon.itask.ITask

class Task3 : ITask<MockTaskBird?> {
    override suspend fun onTask(): MockTaskBird? {
        return getDataFromApi()
    }

    override fun getTaskTag(): Pair<Int, String> {
        return Pair(3, "Task3")
    }

    private fun getDataFromApi(): MockTaskBird? {
        // 模拟网络请求
        Thread.sleep(2000)
        if (System.currentTimeMillis() % 3 == 0L) {
            return MockTaskBird(1.0f, "red", 1)
        }
        return null
    }
}