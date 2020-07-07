package com.app.android.epro.epro.ui.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseFragment
import com.app.android.epro.epro.mvp.contract.ProcessListContract
import com.app.android.epro.epro.mvp.model.bean.ProcessListBean
import com.app.android.epro.epro.mvp.presenter.ProcessListPresenter
import com.app.android.epro.epro.ui.activity.DetailCarRepairActivity
import com.app.android.epro.epro.ui.activity.DetailCarUseActivity
import com.app.android.epro.epro.ui.activity.DetailsIntroductionLetterActivity
import com.app.android.epro.epro.ui.adapter.ProcessListAdapter
import com.app.android.epro.epro.utils.CustomUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_approve.*
import kotlinx.android.synthetic.main.fragment_ask.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.content_list
import kotlinx.android.synthetic.main.fragment_detail.refreshLayout
import kotlinx.android.synthetic.main.fragment_detail.text1
import kotlinx.android.synthetic.main.fragment_detail.text2
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import top.wefor.circularanim.CircularAnim

class DetailFragment : BaseFragment(), ProcessListContract.View {
    private val mPresenter by lazy { ProcessListPresenter() }
    private var mAdapter: ProcessListAdapter? = null
    private var dateList = ArrayList<ProcessListBean.Data.ProcessData>()
    private var page: Int = 1
    private var params = HashMap<String, String>()

    companion object {
        fun getInstance(): DetailFragment {
            return DetailFragment()
        }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun initView() {
        mPresenter.attachView(this)

        initParams()
        initAdapter()

        text1.setOnClickListener {

            if (refreshLayout.autoRefresh()) {
                params["approvelState"] = "1"
                text1.setTextColor(resources.getColor(R.color.color_white))
                text2.setTextColor(resources.getColor(R.color.custom_actionBar))
                text1.setBackgroundResource(R.drawable.shape_bg_approve_text1)
                text2.setBackgroundResource(R.drawable.shape_bg_approve_text2)
                content_list.smoothScrollToPosition(0)

            }

        }

        text2.setOnClickListener {

            if (refreshLayout.autoRefresh()) {
                params["approvelState"] = "2"
                text1.setTextColor(resources.getColor(R.color.custom_actionBar))
                text2.setTextColor(resources.getColor(R.color.color_white))
                text1.setBackgroundResource(R.drawable.shape_bg_approve_text2)
                text2.setBackgroundResource(R.drawable.shape_bg_approve_text1)
                content_list.smoothScrollToPosition(0)
            }

        }

        refreshLayout.setOnRefreshListener {
            page = 1
            params["currentPage"] = page.toString()
            mPresenter.requestDetailListData(params)
        }

        refreshLayout.setOnLoadMoreListener {
            page += 1
            params["currentPage"] = page.toString()
            mPresenter.requestDetailListData(params)
        }

        mAdapter!!.setOnItemClickListener { adapter, _, position ->
            val bean = adapter.data[position] as ProcessListBean.Data.ProcessData
            jump(bean, bean.flowMenuCode, bean.flowReferenceId, bean.jobId)
        }

    }

    private fun jump(
        bean: ProcessListBean.Data.ProcessData,
        menu: String, id: String, jobId: String
    ) {
        when (bean.flowMenuCode) {
            "MENU_VEHICLE_MAINTENANCE_ADD" -> toAny(
                DetailCarRepairActivity().javaClass,
                menu, id, jobId
            )
            "MENU_VEHICLE_APPLICATION_ADD" -> toAny(
                DetailCarUseActivity().javaClass,
                menu, id, jobId
            )
            "MENU_INTRODUCE_LETTER_ADD" -> toAny(
                DetailsIntroductionLetterActivity().javaClass,
                menu, id, jobId
            )
        }
    }


    private fun toAny(cls: Class<Any>, menu: String, id: String, jobId: String) {
        val intent = Intent(context, cls)
        intent.putExtra("menu", menu)
        intent.putExtra("id", id)
        intent.putExtra("jobId", jobId)
        if (params["approvelState"].equals("1")) {
            intent.putExtra("from", "2")
        } else {
            intent.putExtra("from", "-1")
        }

        startActivity(intent)
        activity?.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)

    }


    private fun initParams() {
        params["approvelState"] = "1"
        params["currentPage"] = page.toString()
        params["pageSize"] = CustomUtils.pageSize.toString()
    }

    private fun initAdapter() {
        content_list.layoutManager = linearLayoutManager
        mAdapter = ProcessListAdapter(dateList)
        mAdapter!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn)
        content_list.adapter = mAdapter
    }

    override fun lazyLoad() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(str: String) {
        params["approvelTitle"] = str
        refreshLayout.autoRefresh()
    }


    override fun setProcessListData(data: ProcessListBean) {
        dateList.clear()
        when (data.code) {
            0 -> {
                dateList = data.data.list
                if (page == 1) {
                    if (dateList.size > 0) {
                        mAdapter!!.setList(dateList)
                        if (mAdapter!!.data.size < CustomUtils.pageSize) {
                            refreshLayout.finishRefreshWithNoMoreData()
                        }
                    } else {
                        mAdapter!!.setList(dateList)
                        mAdapter!!.setEmptyView(R.layout.empty_layout)

                    }
                } else {
                    if (page > data.data.pages) {
                        refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        if (dateList.size > 0) {
                            mAdapter!!.addData(dateList)
                            if (dateList.size < CustomUtils.pageSize) {
                                refreshLayout.finishLoadMoreWithNoMoreData()
                            }
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData()
                        }
                    }
                }
            }
            else -> {
                mAdapter!!.setList(dateList)
                mAdapter!!.setEmptyView(R.layout.empty_layout)
                activity?.let { CustomUtils.errHandle(data.code, data.message, it) }
            }
        }


    }

    override fun setDetailBackData(data: Any) {

    }

    override fun showError(msg: String, errorCode: Int) {
        dateList.clear()
        mAdapter!!.setList(dateList)
        mAdapter!!.setEmptyView(R.layout.empty_layout)
        activity?.let { Toasty.error(it, msg).show() }
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        refreshLayout.autoRefresh()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}