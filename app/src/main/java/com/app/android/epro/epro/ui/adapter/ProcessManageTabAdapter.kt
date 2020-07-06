package com.app.android.epro.epro.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.android.epro.epro.mvp.model.bean.UserInfoBean
import com.app.android.epro.epro.ui.fragment.*


class ProcessManageTabAdapter(
    list: ArrayList<UserInfoBean.Data.Menu.SubMenu>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    private var fragments: MutableList<Fragment> = ArrayList()

    init {
        list.forEach {
            when (it.menuCode) {
                "MENU_STAY_EXAMINE" -> {
                    fragments.add(ApproveFragment.getInstance())
                }
                "MENU_TO_BE_HANDLED" -> {
                    fragments.add(DetailFragment.getInstance())
                }
                "MENU_PROCESS_RETURN" -> {
                    fragments.add(ReturnFragment.getInstance())
                }
                "MENU_PROCESS_SUPERVISION" -> {
                    fragments.add(SuperviseFragment.getInstance())
                }
                "MENU_MY_PROCESS" -> {
                    fragments.add(InfoFragment.getInstance())
                }
                "MENU_ASK_FOR_INFORMATION" -> {
                    fragments.add(AskFragment.getInstance())
                }

            }


        }
    }


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}