package com.lemon.dialoglinkutil.testITask

import com.lemon.dialoglinkutil.testITask.data.MockTaskPerson
import com.lemon.itask.ITask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Task1 : ITask<MockTaskPerson?> {

    override suspend fun onTask(): MockTaskPerson? {
        return getDataFromApi()
    }

    override fun getTaskTag(): Pair<Int, String> {
        return Pair(1, "TaskOne")
    }


    private suspend fun getDataFromApi(): MockTaskPerson? {

        return withContext(Dispatchers.IO) {
            delay(1000)
            if (System.currentTimeMillis() % 2 == 0L) {
                MockTaskPerson("name1", 1, 20, "12345678901", "12345678901@qq.com")
            } else {
                null
            }
        }
    }
}