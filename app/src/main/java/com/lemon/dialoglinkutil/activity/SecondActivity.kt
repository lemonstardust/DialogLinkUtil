package com.lemon.dialoglinkutil.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lemon.dialoglinkutil.databinding.ActivityMainBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
    }

    private fun init() {

        mBinding.apply {
            init.setOnClickListener {
                finish()
            }
        }
    }
}