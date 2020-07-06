package com.app.android.epro.epro.ui.adapter

import androidx.fragment.app.Fragment
import com.app.android.epro.epro.ui.fragment.HomeFragment
import com.app.android.epro.epro.ui.fragment.InfoFragment
import com.app.android.epro.epro.ui.fragment.MyselfFragment
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter

class FragmentAdapter : FragmentNavigatorAdapter {

    private var mFragments = ArrayList<Fragment>()

    init {
        mFragments.clear()
        mFragments.add(HomeFragment.getInstance())
        mFragments.add(InfoFragment.getInstance())
        mFragments.add(MyselfFragment.getInstance())
    }


    override fun onCreateFragment(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getTag(position: Int): String {
        return mFragments[position].javaClass.simpleName
    }

    override fun getCount(): Int {
        return mFragments.size
    }

}