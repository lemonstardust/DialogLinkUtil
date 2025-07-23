package com.lemon.itask

interface ITask<T> {


    /**
     * 延迟任务，调接口
     */
    suspend fun onTask():T?

    fun getTaskTag(): Pair<Int, String>
}