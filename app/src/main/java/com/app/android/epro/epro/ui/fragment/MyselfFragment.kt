package com.app.android.epro.epro.ui.fragment


import android.os.Bundle
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseFragment

class MyselfFragment : BaseFragment() {


    companion object {
        fun getInstance(): MyselfFragment {
            val fragment = MyselfFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_myself
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }
}