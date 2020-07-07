package com.app.android.epro.epro.ui.activity

import android.view.MenuItem
import android.widget.EditText
import com.afollestad.materialdialogs.customview.getCustomView
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseDetailActivity
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.bean.DetailIntroductionBean
import com.app.android.epro.epro.mvp.model.bean.ProcessBean
import com.app.android.epro.epro.mvp.model.bean.SendApprovalInfo
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.ui.fragment.InfoBottomFragment
import com.app.android.epro.epro.utils.CustomUtils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.detail_introduction_letter.*
import kotlinx.android.synthetic.main.include_detail_bar.*
import kotlinx.android.synthetic.main.include_detail_top.*
import org.greenrobot.eventbus.EventBus

class DetailsIntroductionLetterActivity : BaseDetailActivity(), ProcessInfoContract.View,
    InfoBottomFragment.RefreshActivity {

    private lateinit var info: DetailIntroductionBean
    private lateinit var id: String
    private lateinit var menu: String
    private lateinit var jobId: String
    private var from: String = "-1"
    private val mPresenter by lazy { ProcessInfoPresenter() }


    init {
        mPresenter.attachView(this)
    }

    override fun layoutId(): Int {
        return R.layout.detail_introduction_letter
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
        info = data as DetailIntroductionBean
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


    private fun setView(info: DetailIntroductionBean.Data.Object) {
        userName.text = info.createUserName
        phone.text = info.createUserPhone
        orgName.text = info.orgName
        code.text = info.refenceCode
        jobName.text = info.jobName
        unitName.text = info.unitName
        departmentName.text = info.departmentName

        mandataryUserName.text = info.mandataryUserName
        introduceProjectName.text = info.introduceProjectName
        mandataryIdCard.text = info.mandataryIdCard
        mandataryUnitName.text = info.mandataryUnitName
        mandataryDepartmentName.text = info.mandataryDepartmentName
        introduceLetterType.text = info.introduceLetterType
        introduceStartTime.text = info.introduceStartTime
        introduceValidDay.text = info.introduceValidDay.toString()
        customerUnitName.text = info.customerUnitName
        introduceContent.text = info.introduceContent

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