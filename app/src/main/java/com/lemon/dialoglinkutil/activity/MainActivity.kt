package com.lemon.dialoglinkutil.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lemon.dialoglinkutil.databinding.ActivityMainBinding
import com.lemon.dialoglinkutil.dialog.TestDialogOne
import com.lemon.dialoglinkutil.dialog.TestDialogTwo
import com.lemon.dialoglink.base.DialogManager
import com.lemon.dialoglinkutil.dialog.CommonConfirmDialog

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        DialogManager.init(this)
        DialogManager.apply {
            addComponent(TestDialogOne())
            addComponent(TestDialogTwo())
        }


        init()
    }

    private fun init() {

        mBinding.apply {
            init.setOnClickListener {

            }
            btn.setOnClickListener {
//                DialogManager.checkCanShow()
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
            }

            showConfirm.setOnClickListener {
                CommonConfirmDialog(this@MainActivity).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        DialogManager.checkCanShow()
    }


}