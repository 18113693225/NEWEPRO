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
import com.app.android.epro.epro.mvp.model.bean.DetailProjectInitiationBean
import com.app.android.epro.epro.mvp.model.bean.ProcessBean
import com.app.android.epro.epro.mvp.model.bean.SendApprovalInfo
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.ui.adapter.DetailProjectInitiationAdapter
import com.app.android.epro.epro.ui.fragment.InfoBottomFragment
import com.app.android.epro.epro.utils.CustomUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.detail_project_initiation.*
import kotlinx.android.synthetic.main.include_detail_bar.*
import kotlinx.android.synthetic.main.include_detail_top.*
import org.greenrobot.eventbus.EventBus

class DetailProjectInitiationChangeActivity : BaseDetailActivity(), ProcessInfoContract.View,
    InfoBottomFragment.RefreshActivity {

    private lateinit var info: DetailProjectInitiationBean
    private lateinit var id: String
    private lateinit var menu: String
    private lateinit var jobId: String
    private var from: String = "-1"
    private var mAdapter: DetailProjectInitiationAdapter? = null
    private val mPresenter by lazy { ProcessInfoPresenter() }


    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.detail_project_initiation
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
        list!!.isFocusable = false
        list!!.setHasFixedSize(true)
        list!!.layoutManager = linearLayoutManager1

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
        info = data as DetailProjectInitiationBean
        when (info.code) {
            0 -> {
                setView(info.data.`object`)
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
    private fun setView(info: DetailProjectInitiationBean.Data.Object) {
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


        customerUnitName.text = info.customerUnitName
        recordProjectName.text = info.recordProjectName
        keepOnFlag.text = if (info.keepOnFlag == "0") "立项备案" else "设计咨询立项备案"
        recordsType.text = if (info.recordsType == "1") "内部备案" else "外部备案"
        projectRecordIndustryName.text = info.projectRecordIndustryName
        recordNatureName.text = info.recordNatureName
        projectTypeName.text = info.projectTypeName
        projectPlayStarttime.text = info.projectPlayStarttime
        projectPlayEndtime.text = info.projectPlayEndtime
        projectForecastContracttime.text = info.projectForecastContracttime
        projectTotalInvestment.text = info.projectTotalInvestment.toString()
        forecastContracttimeMoney.text = info.forecastContracttimeMoney.toString()
        forecastBusinessMoney.text = info.forecastBusinessMoney.toString()
        recordUpperMoney.text = info.recordUpperMoney

        projectResponsibleUsername.text = info.projectResponsibleUsername
        responsibleProsonOrgName.text = info.responsibleProsonOrgName
        responsibleProsonDeptName.text = info.responsibleProsonDeptName
        responsiblePresonPhone.text = info.responsiblePresonPhone
        recordIsWinTheBidding.text = if (info.recordIsWinTheBidding == "1") "中标" else "未中标"
        projectContent.text = info.projectContent
        recordsRemark.text = info.recordsRemark
        recordPartyCommond.text = info.recordPartyCommond
        recordPartyCommondDepart.text = info.recordPartyCommondDepart
        recordPartyCommondJob.text = info.recordPartyCommondJob
        recordPartyCommondTel.text = info.recordPartyCommondTel
        recordManagerUsername.text = info.recordManagerUsername
        recordManagerDeptName.text = info.recordManagerDeptName
        recordManagerJobName.text = info.recordManagerJobName
        recordMangerTel.text = info.recordMangerTel

        recordPartyLive.text =
            if (info.recordPartyLive.isEmpty()) CustomUtils.emptyInfo else info.recordPartyLive
        recordPartyLiveDepart.text =
            if (info.recordPartyLiveDepart.isEmpty()) CustomUtils.emptyInfo else info.recordPartyLiveDepart
        recordPartyLiveJob.text =
            if (info.recordPartyLiveJob.isEmpty()) CustomUtils.emptyInfo else info.recordPartyLiveJob
        recordPartyLiveTel.text =
            if (info.recordPartyLiveTel.isEmpty()) CustomUtils.emptyInfo else info.recordPartyLiveTel
        customerUnitAdress.text = info.customerUnitAdress
        responsibleProsonTell.text = info.responsibleProsonTell

        responsiblePresonEmail.text =
            if (info.responsiblePresonEmail.isEmpty()) CustomUtils.emptyInfo else info.responsiblePresonEmail
        designUnit.text =
            if (info.designUnit.isEmpty()) CustomUtils.emptyInfo else info.designUnit
        mainDesignUsername.text =
            if (info.mainDesignUsername.isEmpty()) CustomUtils.emptyInfo else info.mainDesignUsername
        mainDesignPhone.text =
            if (info.mainDesignPhone.isEmpty()) CustomUtils.emptyInfo else info.mainDesignPhone
        tenderUnit.text =
            if (info.tenderUnit.isEmpty()) CustomUtils.emptyInfo else info.tenderUnit
        supervisionUnit.text =
            if (info.supervisionUnit.isEmpty()) CustomUtils.emptyInfo else info.supervisionUnit
        tenderType.text =
            if (info.tenderType.isEmpty()) CustomUtils.emptyInfo else info.tenderType


        if (info.keepOnStaffList.isNullOrEmpty()) {
            list_ll.isVisible = false
        } else {
            list_ll.isVisible = true
            mAdapter =
                DetailProjectInitiationAdapter(info.keepOnStaffList as MutableList<DetailProjectInitiationBean.Data.Object.KeepOnStaff>)
            mAdapter!!.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
            list.adapter = mAdapter
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