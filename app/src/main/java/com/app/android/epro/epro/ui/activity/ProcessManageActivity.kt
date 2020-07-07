package com.app.android.epro.epro.ui.activity


import android.graphics.Color
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseActivity
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.bean.ProcessNumBean
import com.app.android.epro.epro.mvp.model.bean.UserInfoBean
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.ui.adapter.ProcessManageTabAdapter
import com.app.android.epro.epro.utils.CustomUtils
import com.google.android.material.tabs.TabLayout
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_process_manage.*
import org.greenrobot.eventbus.EventBus

class ProcessManageActivity : BaseActivity(), ProcessInfoContract.View {

    private var list: ArrayList<UserInfoBean.Data.Menu.SubMenu>? = null
    private var mProcessManageTabAdapter: ProcessManageTabAdapter? = null
    private val mPresenter by lazy { ProcessInfoPresenter() }

    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_process_manage
    }

    override fun initData() {
        list = intent.getSerializableExtra("subMenus") as ArrayList<UserInfoBean.Data.Menu.SubMenu>?

    }

    override fun initView() {
        setTbaLayout()
        setTab()

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
        mProcessManageTabAdapter = list?.let { ProcessManageTabAdapter(it, supportFragmentManager) }
        content_pager.adapter = mProcessManageTabAdapter
        content_pager.offscreenPageLimit = list?.size ?: 0

    }

    private fun setTab() {
        tabLayout.removeAllTabs()
        list?.forEach {
            val tab = tabLayout.newTab()
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
                    tab.tag = "1"
                }
                "MENU_TO_BE_HANDLED" -> {
                    img.setImageResource(R.mipmap.banli)
                    layout.setBackgroundColor(Color.parseColor("#D2A549"))
                    tab.tag = "2"
                }
                "MENU_PROCESS_RETURN" -> {
                    img.setImageResource(R.mipmap.tuihui)
                    layout.setBackgroundColor(Color.parseColor("#C28751"))
                    tab.tag = "3"
                }
                "MENU_PROCESS_SUPERVISION" -> {
                    img.setImageResource(R.mipmap.jiandu)
                    layout.setBackgroundColor(Color.parseColor("#397E7E"))
                    tab.tag = "4"
                }
                "MENU_MY_PROCESS" -> {
                    img.setImageResource(R.mipmap.liuc)
                    layout.setBackgroundColor(Color.parseColor("#CF7F99"))
                    tab.tag = "5"
                }
                "MENU_ASK_FOR_INFORMATION" -> {
                    img.setImageResource(R.mipmap.xunwen)
                    layout.setBackgroundColor(Color.parseColor("#B59191"))
                    tab.tag = "6"
                }

            }
            tab.customView = view
            tabLayout.addTab(tab)
        }
    }


    override fun start() {
        mPresenter.infoNum()
    }

    override fun setDetailInfoData(data: Any) {
        val info = data as ProcessNumBean
        when (info.code) {
            0 -> {
                for (i in 0..tabLayout.tabCount) {
                    val it = tabLayout.getTabAt(i) as TabLayout.Tab
                    val num = it.customView?.findViewById<TextView>(R.id.num)
                    when (it.tag) {
                        "1" -> {
                            if (info.data.statementsNum > 0 && num != null)
                                num.text = info.data.statementsNum.toString()
                        }
                        "2" -> {
                            if (info.data.waitNum > 0 && num != null)
                                num.text = info.data.waitNum.toString()
                        }
                        "3" -> {
                            if (info.data.backNum > 0 && num != null)
                                num.text = info.data.backNum.toString()
                        }
                        "4" -> {
                            if (info.data.supervidsedNum > 0 && num != null)
                                num.text = info.data.supervidsedNum.toString()
                        }
                        "6" -> {
                            if (info.data.askNum > 0 && num != null)
                                num.text = info.data.askNum.toString()
                        }
                    }
                }
            }
            else -> {
                CustomUtils.errHandle(info.code, info.message, this)
            }
        }
    }

    override fun setDetailBackData(data: Any) {

    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun onRestart() {
        super.onRestart()
        mPresenter.infoNum()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}