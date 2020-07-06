package com.app.android.epro.epro.ui.activity


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseActivity
import com.app.android.epro.epro.mvp.model.bean.UserInfoBean
import com.app.android.epro.epro.ui.adapter.ProcessManageTabAdapter
import com.app.android.epro.epro.ui.fragment.ApproveFragment
import kotlinx.android.synthetic.main.activity_process_manage.*
import org.greenrobot.eventbus.EventBus

class ProcessManageActivity : BaseActivity() {

    private lateinit var list: ArrayList<UserInfoBean.Data.Menu.SubMenu>
    private var mProcessManageTabAdapter: ProcessManageTabAdapter? = null


    override fun layoutId(): Int {
        return R.layout.activity_process_manage
    }

    override fun initData() {
        list = intent.getSerializableExtra("subMenus") as ArrayList<UserInfoBean.Data.Menu.SubMenu>


    }

    override fun initView() {
        setTbaLayout()
        tabLayout.removeAllTabs()
        list.forEach {

            val view = LayoutInflater.from(this).inflate(R.layout.tab_view, null)
            val name = view.findViewById<TextView>(R.id.name)
            val img = view.findViewById<ImageView>(R.id.img)
            val layout = view.findViewById<RelativeLayout>(R.id.view_ll)

            if ("zh" == resources.configuration.locale.language) {
                name.text = it.menuNameTxt[0].menuNam
            } else {
                name.text = it.menuNameTxt[1].menuNam
            }
            when (it.menuCode) {
                "MENU_STAY_EXAMINE" -> {
                    img.setImageResource(R.mipmap.shenhe)
                    layout.setBackgroundColor(Color.parseColor("#478738"))
                }
                "MENU_TO_BE_HANDLED" -> {
                    img.setImageResource(R.mipmap.banli)
                    layout.setBackgroundColor(Color.parseColor("#D2A549"))
                }
                "MENU_PROCESS_RETURN" -> {
                    img.setImageResource(R.mipmap.tuihui)
                    layout.setBackgroundColor(Color.parseColor("#C28751"))
                }
                "MENU_PROCESS_SUPERVISION" -> {
                    img.setImageResource(R.mipmap.jiandu)
                    layout.setBackgroundColor(Color.parseColor("#397E7E"))
                }
                "MENU_MY_PROCESS" -> {
                    img.setImageResource(R.mipmap.liuc)
                    layout.setBackgroundColor(Color.parseColor("#CF7F99"))
                }
                "MENU_ASK_FOR_INFORMATION" -> {
                    img.setImageResource(R.mipmap.xunwen)
                    layout.setBackgroundColor(Color.parseColor("#B59191"))
                }

            }
            val tab = tabLayout.newTab()
            tab.customView = view
            tabLayout.addTab(tab)
        }

        back.setOnClickListener {
            finishAfterTransition()
        }

        val doAfterTextChanged = search.doAfterTextChanged {
            EventBus.getDefault().post(it.toString())
            closeKeyBord(search, this)

        }
        search.addTextChangedListener(doAfterTextChanged)

    }


    private fun setTbaLayout() {
        tabLayout.setupWithViewPager(content_pager)
        mProcessManageTabAdapter = ProcessManageTabAdapter(list, supportFragmentManager)
        content_pager.adapter = mProcessManageTabAdapter
        content_pager.offscreenPageLimit = list.size

    }

    override fun start() {

    }


}