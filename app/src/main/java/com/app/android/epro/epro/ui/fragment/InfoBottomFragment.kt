package com.app.android.epro.epro.ui.fragment


import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView


import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseFragment
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.bean.ApprovalInfoBean
import com.app.android.epro.epro.mvp.model.bean.ProcessBean
import com.app.android.epro.epro.mvp.model.bean.SendApprovalInfo
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.ui.adapter.ProcessInfoAdapter
import com.app.android.epro.epro.ui.adapter.ProcessMessageAdapter
import com.app.android.epro.epro.utils.CustomUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import es.dmoral.toasty.Toasty

import kotlinx.android.synthetic.main.fragment_info_bottom.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class InfoBottomFragment : BaseFragment(), ProcessInfoContract.View,
    ProcessInfoAdapter.ClickShowDialog {

    private val mPresenter by lazy { ProcessInfoPresenter() }
    private var mAdapter1: ProcessInfoAdapter? = null
    private var mAdapter2: ProcessMessageAdapter? = null
    private var params = HashMap<String, String>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_info_bottom
    }

    override fun initView() {
        mPresenter.attachView(this)
        setRecyclerView()

    }

    override fun lazyLoad() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(info: ApprovalInfoBean) {
        initAdapter1(info.statements as MutableList<MutableList<ApprovalInfoBean.Statement>>)
        initAdapter2(info.messageList as MutableList<ApprovalInfoBean.Message>)

        params["messMenuCode"] = info.sheet.flowMenuCode
        params["messReferenceId"] = info.sheet.flowReferenceId


    }

    private fun initAdapter1(info: MutableList<MutableList<ApprovalInfoBean.Statement>>) {
        if (info.size < 1) {
            flow_ll.isVisible = false
        } else {
            flow_ll.isVisible = true
            mAdapter1 = ProcessInfoAdapter(info)
            mAdapter1!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
            list1.adapter = mAdapter1
            mAdapter1!!.setClickListener(this)

        }
    }

    private fun initAdapter2(info: MutableList<ApprovalInfoBean.Message>) {
        if (info.size < 1) {
            ask_ll.isVisible = false
        } else {
            ask_ll.isVisible = true
            mAdapter2 = ProcessMessageAdapter(info)
            mAdapter2!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
            list2.adapter = mAdapter2

            mAdapter2!!.addChildClickViewIds(R.id.right_ll)
            mAdapter2!!.setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.right_ll) {
                    mPresenter.recallMsg(info[position].approvalMessageId)
                }
            }
        }


    }

    private fun setRecyclerView() {

        val linearLayoutManager1: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        val linearLayoutManager2: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        //list1!!.isFocusable = false
        list1!!.setHasFixedSize(true)
        list1!!.layoutManager = linearLayoutManager1


        list2!!.isFocusable = false
        list2!!.setHasFixedSize(true)
        list2!!.layoutManager = linearLayoutManager2

    }


    override fun setDetailInfoData(data: Any) {

    }

    override fun setDetailBackData(data: Any) {
        val info = data as ProcessBean
        when (info.code) {
            0 -> {
                activity?.let { Toasty.info(it, "success").show() }

                if (activity is RefreshActivity) {
                    (activity as RefreshActivity?)?.renovate()
                }

            }
            else -> {
                activity?.let { CustomUtils.errHandle(data.code, data.message, it) }
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        activity?.let { Toasty.error(it, msg).show() }
    }

    override fun showLoading() {
        activity?.let { CustomUtils.showPostDialog(it) }
    }

    override fun dismissLoading() {
        CustomUtils.dismissDialog()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    interface RefreshActivity {
        fun renovate()

    }

    override fun show(item: ApprovalInfoBean.Statement) {
        params["messSheetId"] = item.flowSheetId
        if (item.statementsNowStep == 1) {
            params["messReplyUid"] = item.statementsFromUserid
            params["messReplyUname"] = item.statementsFromUsername
        } else {
            params["messReplyUid"] = item.shoudApprovalUserId
            params["messReplyUname"] = item.shoudApprovalUserName
        }
        showInputDialog()
    }

    private fun showInputDialog() {

        activity?.let {
            MaterialDialog(it).show {
                title(R.string.ask)
                cancelOnTouchOutside(false)
                customView(R.layout.dialog_ask_content)


                positiveButton(R.string.preservation) {
                    val input: EditText = getCustomView().findViewById(R.id.note_et)
                    if (input.text.toString().isEmpty()) {
                        return@positiveButton
                    } else {
                        params["messContent"] = input.text.toString()
                        mPresenter.keepMsg(CustomUtils.toBody(params))
                    }
                    dismiss()

                }
                    .negativeButton(R.string.cancel) {
                        dismiss()
                    }
            }

        }
    }

}