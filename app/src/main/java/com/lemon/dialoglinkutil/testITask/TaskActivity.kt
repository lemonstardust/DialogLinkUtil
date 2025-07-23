package com.lemon.dialoglinkutil.testITask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lemon.dialoglinkutil.databinding.ActivityTaskBinding
import com.lemon.dialoglinkutil.testITask.data.MockTaskBike
import com.lemon.dialoglinkutil.testITask.data.MockTaskBird
import com.lemon.dialoglinkutil.testITask.data.MockTaskPerson
import com.lemon.itask.ITaskListener
import com.lemon.itask.TaskLogic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TaskActivity : AppCompatActivity(), ITaskListener {
    private lateinit var mBinding: ActivityTaskBinding

    private val taskLogic by lazy { TaskLogic() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
    }

    private fun init() {

        mBinding.apply {
            startTask.setOnClickListener {
                taskLogic.startTask()
            }
        }
        taskLogic.run {
            addListener(this@TaskActivity)


            addTask(Task2())
            addTask(Task3())

            addTask(Task1())
        }
    }

    override fun onReceiveTask(data: Any?) {
        when (data) {
            is MockTaskPerson -> {
                mBinding.result.text =
                    "MockTaskPerson name:${data.name},age:${data.age},sex:${data.sex},phone:${data.phone},email:${data.email}"
            }

            is MockTaskBird -> {
                mBinding.result.text =
                    "MockTaskBird weight:${data.weight},featherColor:${data.featherColor},eyeColor:${data.eyeColor}"
            }

            is MockTaskBike -> {
                mBinding.result.text =
                    "MockTaskBike name:${data.name},price:${data.price},count:${data.count}"
            }
        }
    }

    override fun onDispatch(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}