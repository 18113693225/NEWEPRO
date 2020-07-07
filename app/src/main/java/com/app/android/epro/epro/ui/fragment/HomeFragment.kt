package com.app.android.epro.epro.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseFragment
import com.app.android.epro.epro.mvp.contract.HomeContract
import com.app.android.epro.epro.mvp.model.bean.UserInfoBean
import com.app.android.epro.epro.mvp.presenter.HomePresenter
import com.app.android.epro.epro.rx.scheduler.SchedulerUtils
import com.app.android.epro.epro.ui.activity.ProcessManageActivity
import com.app.android.epro.epro.ui.adapter.HomeMenuAdapter
import com.app.android.epro.epro.utils.CustomUtils
import com.app.android.epro.epro.utils.StatusBarUtil
import com.scwang.smart.refresh.layout.util.SmartUtil
import es.dmoral.toasty.Toasty
import io.reactivex.rxjava3.internal.operators.observable.ObservableRangeLong
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment(), HomeContract.View {


    private val mPresenter by lazy { HomePresenter() }
    private var mHomeMenuAdapter: HomeMenuAdapter? = null
    private var menuList = ArrayList<UserInfoBean.Data.Menu>()
    private var timeStatus: Boolean = true

    private var mScrollY = 0
    private var lastScrollY = 0

    private var h: Int = SmartUtil.dp2px(100F)

    private val simpleDateFormat by lazy {
        SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
    }

    companion object {
        fun getInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val gridLayoutManager by lazy {
        GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        mPresenter.attachView(this)
        initAdapter()
        toolbar.background.mutate().alpha = 0
        //设置状态栏
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if (lastScrollY < h) {
                mScrollY = scrollY.coerceAtMost(h)
                parallax.translationY = -mScrollY.toFloat()
                toolbar.background.mutate().alpha = 255 * mScrollY / h
                title.visibility = View.GONE
            } else {
                title.visibility = View.VISIBLE
                title.text = resources.getText(R.string.home)
            }
            lastScrollY = scrollY
        })

        mHomeMenuAdapter?.setOnItemClickListener { adapter, view, position ->
            val menu: UserInfoBean.Data.Menu = adapter.data[position] as UserInfoBean.Data.Menu

            when (menu.menuCode) {
                "MEN_FLOW_APPROVAL" -> toProcessManageActivity(
                    menu.subMenus as ArrayList<UserInfoBean.Data.Menu.SubMenu>,
                    view
                )
                else -> context?.let { Toasty.error(it, "功能开发中").show() }
            }


        }

    }

    private fun initAdapter() {
        pageView.layoutManager = gridLayoutManager
        mHomeMenuAdapter = HomeMenuAdapter(menuList)
        pageView.adapter = mHomeMenuAdapter
    }

    override fun lazyLoad() {
        mPresenter.requestUserInfoData()
    }


    override fun setUserInfoData(userInfoBean: UserInfoBean) {
        when (userInfoBean.code) {

            0 -> {
                mHomeMenuAdapter?.setList(userInfoBean.data.menus)
            }
            else -> {
                activity?.let { CustomUtils.errHandle(userInfoBean.code, userInfoBean.message, it) }
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        activity?.let { Toasty.error(it, msg).show() }
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }


    private fun getTime() {
        ObservableRangeLong.interval(0, 1, TimeUnit.SECONDS)
            .compose(SchedulerUtils.ioToMain())
            .takeWhile {
                timeStatus
            }
            .subscribe {
                title.text = simpleDateFormat.format(System.currentTimeMillis())
            }
    }


    override fun onStart() {
        super.onStart()
        timeStatus = true
    }

    override fun onPause() {
        super.onPause()
        timeStatus = false
    }


    private fun toProcessManageActivity(
        list: ArrayList<UserInfoBean.Data.Menu.SubMenu>,
        view: View
    ) {
        val intent = Intent(context, ProcessManageActivity::class.java)
        intent.putExtra("subMenus", list)

        val options = activity?.let {
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                it, view, "menu_ly"
            )
        }
        startActivity(intent, options?.toBundle())

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }


}