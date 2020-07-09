package com.app.android.epro.epro.ui.activity

import android.annotation.SuppressLint
import android.view.MenuItem
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.customview.getCustomView
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseDetailActivity
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.bean.DetailSalesInvoiceBean
import com.app.android.epro.epro.mvp.model.bean.ProcessBean
import com.app.android.epro.epro.mvp.model.bean.SendApprovalInfo
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.ui.adapter.DetailSaleInvoiceAdapter1
import com.app.android.epro.epro.ui.adapter.DetailSaleInvoiceAdapter2
import com.app.android.epro.epro.ui.fragment.InfoBottomFragment
import com.app.android.epro.epro.utils.CustomUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.detail_project_initiation.list
import kotlinx.android.synthetic.main.detail_sales_invoice.*
import kotlinx.android.synthetic.main.include_detail_bar.*
import kotlinx.android.synthetic.main.include_detail_top.*
import org.greenrobot.eventbus.EventBus

class DetailSaleInvoiceActivity : BaseDetailActivity(), ProcessInfoContract.View,
    InfoBottomFragment.RefreshActivity {

    private lateinit var info: DetailSalesInvoiceBean
    private lateinit var id: String
    private lateinit var menu: String
    private lateinit var jobId: String
    private var from: String = "-1"
    private var mAdapter1: DetailSaleInvoiceAdapter1? = null
    private var mAdapter2: DetailSaleInvoiceAdapter2? = null

    private val mPresenter by lazy { ProcessInfoPresenter() }


    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.detail_sales_invoice
    }

    override fun initData() {
        menu = intent.getStringExtra("menu")
        id = intent.getStringExtra("id")
        jobId = intent.getStringExtra("jobId")
        //1待审核 2待办理 3监督
        from = intent.getStringExtra("from")

    }

    override fun initView() {
        setRecyclerView()
        action_update.setOnClickListener {
            mPresenter.requestDetailInfoData(id, menu)
        }
        when (from) {
            "1" -> {
                addRefuseButton()
                addPassButton()
            }
            "2" -> {
                addBackButton()
                addReplayButton()
            }
            "3" -> {
                addFreezeButton()
                addThawButton()
            }

        }

        refuseBt.setOnClickListener {
            dialogStr(1)
        }
        passBt.setOnClickListener {
            CustomUtils.toApproval(this, info.data.dataApproval.sheet.flowApprovalSheetId, jobId)
        }
        freezeBt.setOnClickListener {
            mPresenter.freeze(info.data.dataApproval.sheet.flowApprovalSheetId, "2")
        }
        thawBt.setOnClickListener {
            mPresenter.freeze(info.data.dataApproval.sheet.flowApprovalSheetId, "1")

        }
        backBt.setOnClickListener {
            dialogStr(1)
        }
        replayBt.setOnClickListener {
            dialogStr(2)
        }


    }

    private fun setRecyclerView() {

        val linearLayoutManager1: LinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        val linearLayoutManager2: LinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        list!!.isFocusable = false
        list!!.setHasFixedSize(true)
        list!!.layoutManager = linearLayoutManager1

        list3!!.isFocusable = false
        list3!!.setHasFixedSize(true)
        list3!!.layoutManager = linearLayoutManager2

    }


    private fun dialogStr(type: Int) {

        dialog.show()

        dialog.positiveButton(R.string.preservation) {
            val input: EditText = it.getCustomView().findViewById(R.id.note_et)
            if (input.text.toString().isEmpty()) {
                return@positiveButton
            }
            if (type == 1) {
                mPresenter.goBack(
                    input.text.toString(), info.data.dataApproval.sheet.flowApprovalSheetId
                )
            } else if (type == 2) {
                mPresenter.pass(
                    CustomUtils.toBody(
                        SendApprovalInfo(
                            input.text.toString(), "2", null,
                            info.data.dataApproval.sheet.flowApprovalSheetId, jobId, null
                        )
                    )
                )
            }

            dialog.dismiss()
        }
            .negativeButton(R.string.cancel) {
                dialog.dismiss()
            }


    }


    override fun start() {
        mPresenter.requestDetailInfoData(id, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAfterTransition()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setDetailInfoData(data: Any) {
        info = data as DetailSalesInvoiceBean
        when (info.code) {
            0 -> {
                setView(
                    info.data.`object`,
                    info.data.invoicedTotalList,
                    info.data.thisInvoicedTotal.toString(),
                    info.data.taxInvoicedTotal.toString()
                )
                EventBus.getDefault().post(info.data.dataApproval)
            }
            else -> {
                CustomUtils.errHandle(info.code, info.message, this)
            }
        }
    }

    override fun setDetailBackData(data: Any) {
        val info = data as ProcessBean
        when (info.code) {
            0 -> {
                Toasty.info(this, "success").show()
                finishAfterTransition()
            }
            else -> {
                CustomUtils.errHandle(info.code, info.message, this)
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setView(
        info: DetailSalesInvoiceBean.Data.Object,
        totalList: List<DetailSalesInvoiceBean.Data.Object>,
        thisInvoiced: String,
        taxInvoiced: String

    ) {
        userName.text = info.createUserName
        phone.text = info.createUserPhone
        orgName.text = info.orgName
        when (info.approvalState) {
            "1" -> {
                status_tv.text = resources.getText(R.string.To_be_reviewed)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_wait)
            }
            "2" -> {
                status_tv.text = resources.getText(R.string.Under_review)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_ing)
            }
            "3" -> {
                status_tv.text = resources.getText(R.string.Approved)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_yes)
            }
            "4" -> {
                status_tv.text = resources.getText(R.string.Fail)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_no)
            }
            "5" -> {
                status_tv.text = resources.getText(R.string.Back)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_no)
            }
            "6" -> {
                status_tv.text = resources.getText(R.string.withdraw)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_wait)
            }
            "7" -> {
                status_tv.text = resources.getText(R.string.cancel)
                status_tv.setBackgroundResource(R.drawable.shape_bg_approve_wait)
            }

        }
        code.text = info.refenceCode
        jobName.text = info.jobName
        unitName.text = info.unitName
        departmentName.text = info.departmentName


        applicationContractName.text = info.applicationContractName
        applicationContractMoney.text = info.applicationContractMoney.toString()
        applicationHaveMoney.text = info.applicationHaveMoney.toString()
        applicationHaveInvoiced.text = info.applicationHaveInvoiced.toString()
        applicationSurplusInvoiced.text = info.applicationSurplusInvoiced.toString()
        applicationUserName.text = info.applicationUserName
        applicationSendType.text = when (info.applicationSendType) {
            "1" -> "快递"
            "2" -> "领票人送票"
            "3" -> "对方上门取票"
            else -> CustomUtils.emptyInfo
        }
        applicationInvoiceType.text =
            if (info.applicationInvoiceType == "1") "增值税专用发票" else "增值税普通发票"


        applicationSendAdress.text =
            if (info.applicationSendAdress.isEmpty()) CustomUtils.emptyInfo else info.applicationSendAdress

        applicationNotice.text =
            if (info.applicationNotice.isEmpty()) CustomUtils.emptyInfo else info.applicationNotice

        applicationBuyName.text = info.applicationBuyName
        applicationBuyTax.text = info.applicationBuyTax
        applicationBuyBank.text = info.applicationBuyBank
        applicationBuyAddress.text = info.applicationBuyAddress

        applicationSaleName.text =
            if (info.applicationSaleName.isEmpty()) CustomUtils.emptyInfo else info.applicationSaleName

        applicationSaleTax.text =
            if (info.applicationSaleTax.isEmpty()) CustomUtils.emptyInfo else info.applicationSaleTax

        applicationSaleBank.text =
            if (info.applicationSaleBank.isEmpty()) CustomUtils.emptyInfo else info.applicationSaleBank

        applicationSaleAddress.text =
            if (info.applicationSaleAddress.isEmpty()) CustomUtils.emptyInfo else info.applicationSaleAddress


        if (info.formDetailsList.isNullOrEmpty()) {
            list_ll.isVisible = false
        } else {
            list_ll.isVisible = true
            applicationThisInvoiced.text = info.applicationThisInvoiced.toString()
            applicationUpperInvoiced.text = info.applicationUpperInvoiced
            applicationTaxInvoiced.text = info.applicationTaxInvoiced.toString()
            mAdapter1 =
                DetailSaleInvoiceAdapter1(info.formDetailsList as MutableList<DetailSalesInvoiceBean.Data.Object.FormDetails>)
            mAdapter1!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
            list.adapter = mAdapter1
        }

        if (totalList.isNullOrEmpty()) {
            list3_ll.isVisible = false
        } else {
            list3_ll.isVisible = true
            thisInvoicedTotal.text = thisInvoiced
            taxInvoicedTotal.text = taxInvoiced
            mAdapter2 =
                DetailSaleInvoiceAdapter2(totalList as MutableList<DetailSalesInvoiceBean.Data.Object>)
            mAdapter2!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
            list3.adapter = mAdapter2
        }

    }

    override fun showError(msg: String, errorCode: Int) {
        Toasty.error(this, msg).show()
    }

    override fun showLoading() {
        CustomUtils.showPostDialog(this)
    }

    override fun dismissLoading() {
        CustomUtils.dismissDialog()
    }

    override fun onRestart() {
        super.onRestart()
        mPresenter.requestDetailInfoData(id, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun renovate() {
        mPresenter.requestDetailInfoData(id, menu)
    }


}