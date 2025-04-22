package com.lemon.dialoglinkutil.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import com.lemon.dialoglink.base.IDialogLifecycles
import com.lemon.dialoglinkutil.R
import com.lemon.dialoglinkutil.databinding.DialogCommonConfirmBinding


class CommonConfirmDialog(context: Context) : Dialog(context, R.style.BaseDialogStyle),IDialogLifecycles {

    private lateinit var mBinding: DialogCommonConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        mBinding = DialogCommonConfirmBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // 设置根据XML文件的大小显示
//        window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )

        init()
    }

    private fun init() {

        mBinding.btnNegative.setOnClickListener {
            dismiss()
        }

        mBinding.btnPositive.setOnClickListener {
            dismiss()
        }
    }


    override fun show() {
        super.show()

        val dialogWindow = window
        if (dialogWindow != null) {
            val lp = dialogWindow.attributes
//            dialogWindow.setGravity(Gravity.START or Gravity.TOP)
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialogWindow.attributes = lp
        }
    }

    override fun showDialog() {
        TODO("Not yet implemented")
    }

    override fun onShow() {
    }

    override fun onDismiss() {
    }
}