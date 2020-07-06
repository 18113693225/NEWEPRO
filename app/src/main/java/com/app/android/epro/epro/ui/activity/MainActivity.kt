package com.app.android.epro.epro.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseActivity
import com.app.android.epro.epro.ui.adapter.FragmentAdapter
import com.aspsine.fragmentnavigator.FragmentNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    //默认为0
    private var mIndex = 0
    private lateinit var mNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTab(savedInstanceState)
        switchFragment()
    }

    private fun initTab(savedInstanceState: Bundle?) {

        mNavigator = FragmentNavigator(
            supportFragmentManager,
            FragmentAdapter(), R.id.fl_container
        )
        mNavigator.setDefaultPosition(mIndex)
        mNavigator.onCreate(savedInstanceState)
        mNavigator.showFragment(mNavigator.currentPosition)
    }

    private fun switchFragment() {
        tab_layout.setOnTabChangedListner {
            when (it) {
                0 -> mNavigator?.showFragment(0)
                1 -> mNavigator?.showFragment(1)
                2 -> mNavigator?.showFragment(2)

            }

        }
    }


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun start() {

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mNavigator.onSaveInstanceState(outState)
    }


}
