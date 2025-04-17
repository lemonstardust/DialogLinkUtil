package com.lemon.dialoglinkutil.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.lemon.dialoglinkutil.R
import com.lemon.dialoglinkutil.databinding.CommonDialogBinding

class FunctionDialogOne : DialogFragment(), View.OnClickListener {


    private lateinit var mBinding: CommonDialogBinding


    companion object {

        fun show(fragmentManager: FragmentManager, title: String, content: String) {
            val fragment = FunctionDialogOne()
            val bundle = Bundle().apply {
                putString("title", title)
                putString("content", content)
            }
            fragment.arguments = bundle
            fragmentManager.beginTransaction()
                .add(fragment, FunctionDialogOne::class.java.simpleName)
                .commit()
            Log.e("TAG", "fragemnt tag = ${fragment.tag}")

        }

        fun close(fragmentManager: FragmentManager) {
            val fragment =
                fragmentManager.findFragmentByTag(FunctionDialogOne::class.java.simpleName)
                    ?: return
            fragmentManager.beginTransaction().remove(fragment).commit()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = CommonDialogBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        mBinding.cancel.setOnClickListener(this)
        mBinding.confirm.setOnClickListener(this)
        val title = arguments?.getString("title") ?: ""
        val content = arguments?.getString("content") ?: ""
        setTitle(title)
        setContent(content)
    }

    private fun setTitle(s: String) {
        mBinding.title.text = s
    }

    private fun setContent(s: String) {
        mBinding.content.text = s
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cancel -> {

                dismissAllowingStateLoss()
            }

            R.id.confirm -> {

                dismissAllowingStateLoss()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        // 设置宽度为铺满
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

//    override fun show(manager: FragmentManager, tag: String?) {
//        super.show(manager, tag)
//    }


}