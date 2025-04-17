package com.lemon.dialoglinkutil.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.lemon.dialoglinkutil.R
import com.lemon.dialoglinkutil.databinding.SingleBtnDialogBinding

class FunctionDialogTwo : DialogFragment(), View.OnClickListener {


    private lateinit var mBinding: SingleBtnDialogBinding


    companion object {

        fun show(fragmentManager: FragmentManager, title: String, content: String) {
            val fragment = FunctionDialogTwo()
            val bundle = Bundle().apply {
                putString("title", title)
                putString("content", content)
            }
            fragment.arguments = bundle
            fragmentManager.beginTransaction()
                .add(fragment, FunctionDialogTwo::class.java.simpleName)
                .commit()
            Log.e("TAG", "fragemnt tag = ${fragment.tag}")

        }

        fun close(fragmentManager: FragmentManager) {
            val fragment =
                fragmentManager.findFragmentByTag(FunctionDialogTwo::class.java.simpleName)
                    ?: return
            fragmentManager.beginTransaction().remove(fragment).commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = SingleBtnDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        mBinding = SingleBtnDialogBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
//        mBinding.cancel.setOnClickListener(this)
        val title = arguments?.getString("title") ?: ""
        val content = arguments?.getString("content") ?: ""
        setTitle(title)
        setContent(content)
        mBinding.confirm.setOnClickListener(this)
    }

    private fun setTitle(s: String) {
        mBinding.title.text = s
    }

    private fun setContent(s: String) {
        mBinding.content.text = s
    }

    override fun onClick(view: View) {
        when (view.id) {
//            R.id.cancel -> {
//
//                dismiss()
//            }

            R.id.confirm -> {

                dismissAllowingStateLoss()
            }

        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        // 设置宽度为铺满
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

//    override fun show() {
//        super.show()
////        window?.setBackgroundDrawableResource(android.R.color.transparent)
//        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        window?.setGravity(Gravity.CENTER)
//    }


}