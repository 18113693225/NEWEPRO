package com.app.android.epro.epro.ui.activity

import android.annotation.SuppressLint
import android.view.MenuItem
import android.widget.EditText
import com.afollestad.materialdialogs.customview.getCustomView
import com.app.android.epro.epro.R
import com.app.android.epro.epro.api.UrlConstant
import com.app.android.epro.epro.base.BaseDetailActivity
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.bean.DetailFormProjectLaborPersonnelBean
import com.app.android.epro.epro.mvp.model.bean.ProcessBean
import com.app.android.epro.epro.mvp.model.bean.SendApprovalInfo
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.ui.fragment.InfoBottomFragment
import com.app.android.epro.epro.utils.CustomUtils
import com.bumptech.glide.Glide
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.detail_form_project_labor_personnel.*
import kotlinx.android.synthetic.main.include_detail_bar.*
import kotlinx.android.synthetic.main.include_detail_top.*
import org.greenrobot.eventbus.EventBus

class DetailFormProjectLaborPersonnelActivity : BaseDetailActivity(), ProcessInfoContract.View,
    InfoBottomFragment.RefreshActivity {

    private lateinit var info: DetailFormProjectLaborPersonnelBean
    private lateinit var id: String
    private lateinit var menu: String
    private lateinit var jobId: String
    private var from: String = "-1"
    private val mPresenter by lazy { ProcessInfoPresenter() }


    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.detail_form_project_labor_personnel
    }

    override fun initData() {
        menu = intent.getStringExtra("menu")
        id = intent.getStringExtra("id")
        jobId = intent.getStringExtra("jobId")
        //1待审核 2待办理 3监督
        from = intent.getStringExtra("from")

    }

    override fun initView() {
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
        info = data as DetailFormProjectLaborPersonnelBean
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
    private fun setView(info: DetailFormProjectLaborPersonnelBean.Data.Object) {
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

        workerRecordType.text =
            if (info.workerRecordType == "1") "临时劳务" else "分包劳务"

        workerContractName.text = info.workerContractName
        workerProjectManagerName.text = info.workerProjectManagerName
        workerUserName.text = info.workerUserName
        workerSex.text =
            if (info.workerSex == "1") "男" else "女"
        workerIdCard.text = info.workerIdCard
        workerPhone.text = info.workerPhone
        workerAddress.text = info.workerAddress
        workerJobType.text = info.workerJobType
        workerClass.text = info.workerClass
        workerJobState.text = info.workerJobState
        workerBankName.text = info.workerBankName
        workerBankNumber.text = info.workerBankNumber
        workerDateMoney.text = info.workerDateMoney.toString()
        workerEntryTime.text = info.workerEntryTime
        workerLeaveTime.text = info.workerLeaveTime
        workerEmergencyName.text = info.workerEmergencyName
        worlerEmergencyPhone.text = info.worlerEmergencyPhone

        Glide.with(this)
            .load(CustomUtils.getTokenGlideUrl(UrlConstant.File_URL + info.workerFaceUrl))
            .into(workerFaceUrl)
        Glide.with(this)
            .load(CustomUtils.getTokenGlideUrl(UrlConstant.File_URL + info.workerCardUrl))
            .into(workerCardUrl)

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