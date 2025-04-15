package com.lemon.dialoglinkutil.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lemon.dialoglinkutil.databinding.ActivityMainBinding
import com.lemon.dialoglinkutil.dialog.TestDialogOne
import com.lemon.dialoglinkutil.dialog.TestDialogTwo
import com.lemon.dialoglinkutil.dialog.base.DialogManager

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
    }

    private fun init() {
        DialogManager.init(this)
        DialogManager.apply {
            addComponent(TestDialogOne())
            addComponent(TestDialogTwo())
        }
        mBinding.apply {
            btn.setOnClickListener {
                DialogManager.checkCanShow()
            }
        }
    }
}