package com.app.android.epro.epro.ui.fragment


import android.os.Bundle
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseFragment

class InfoFragment : BaseFragment() {

    companion object {
        fun getInstance(): InfoFragment {
            val fragment = InfoFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }



    override fun getLayoutId(): Int {
        return R.layout.fragment_info
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }
}