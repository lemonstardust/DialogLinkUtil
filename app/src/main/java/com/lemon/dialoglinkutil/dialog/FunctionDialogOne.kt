package com.lemon.dialoglinkutil.dialog

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

        fun show(fragmentManager: FragmentManager, bundle: Bundle? = null) {
            val fragment = FunctionDialogOne()
            fragment.arguments = bundle
            Log.e("TAG", "fragemnt tag = ${fragment.tag}")
            fragmentManager.beginTransaction()
                .add(fragment, FunctionDialogOne::class.java.simpleName)
                .commit()
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
    }

    fun setTitle(s: String) {
        mBinding.title.text = s
    }

    fun setContent(s: String) {
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

//    override fun show(manager: FragmentManager, tag: String?) {
//        super.show(manager, tag)
//    }



//    override fun show() {
//        super.show()
////        window?.setBackgroundDrawableResource(android.R.color.transparent)
//        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        window?.setGravity(Gravity.CENTER)
//    }


}