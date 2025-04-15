package com.lemon.dialoglinkutil.mock

import com.lemon.dialoglinkutil.data.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object MockApi {

    suspend fun getStringApi(param: String): String {

        val result = withContext(Dispatchers.IO) {
            delay(800)
            "getStringApi $param"
        }
        return result
    }


    suspend fun getIntApi(param: Int): Int {

        val result = withContext(Dispatchers.IO) {
            delay(1000)
            param + 100
        }
        return result
    }

    suspend fun getUserInfo(name: String, age: Int, sex: Int): UserInfo {

        val result = withContext(Dispatchers.IO) {
            delay(2000)
            val userInfo = UserInfo(name, age, sex)
            return@withContext userInfo
        }
        return result
    }

}