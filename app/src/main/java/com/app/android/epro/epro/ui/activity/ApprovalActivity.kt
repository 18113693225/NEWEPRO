package com.app.android.epro.epro.ui.activity


import android.view.MenuItem
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseActivity
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.bean.NextPeopleBean
import com.app.android.epro.epro.mvp.model.bean.ProcessBean
import com.app.android.epro.epro.mvp.model.bean.SendApprovalInfo
import com.app.android.epro.epro.mvp.presenter.ProcessInfoPresenter
import com.app.android.epro.epro.utils.CustomUtils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_approval.*


class ApprovalActivity : BaseActivity(), ProcessInfoContract.View {

    private lateinit var info: NextPeopleBean
    private var people = ArrayList<String>()
    private lateinit var flowApprovalSheetId: String
    private lateinit var jobId: String
    private var num: Int = -2
    private var nextList = ArrayList<NextPeopleBean.Data.StaffRecord>()
    private val mPresenter by lazy { ProcessInfoPresenter() }

    init {
        mPresenter.attachView(this)
    }


    override fun layoutId(): Int {
        return R.layout.activity_approval
    }


    override fun initData() {
        flowApprovalSheetId = intent.getStringExtra("flowApprovalSheetId")
        jobId = intent.getStringExtra("jobId")
    }

    override fun initView() {
        select_people_ll.setOnClickListener {
            showPeopleDialog()
        }
        commit.setOnClickListener {
            if (approvalOpinion_tv.text.isNullOrEmpty()) {
                Toasty.error(this, R.string.please_input_approval_content).show()
                return@setOnClickListener
            }
            if (num == -2) {
                Toasty.error(this, R.string.please_select_the_next_reviewer).show()
                return@setOnClickListener
            }

            mPresenter.pass(
                CustomUtils.toBody(
                    SendApprovalInfo(
                        approvalOpinion_tv.text.toString(), "2", null,
                        flowApprovalSheetId, jobId, nextList
                    )
                )
            )
        }
    }

    override fun start() {
        mPresenter.nextPeople(flowApprovalSheetId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAfterTransition()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setDetailInfoData(data: Any) {
        info = data as NextPeopleBean
        when (info.code) {
            0 -> {
                if (info.data.isLastApproval && "2" == info.data.isSign) {
                    takeData(info.data.staffRecordList)
                } else {
                    num = -1
                    nextList.addAll(info.data.staffRecordList)
                    select_name_tv.text = "会签流程，无需选择下一步审核人"
                }
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
                CustomUtils.toProcessManageActivity(this)
            }
            else -> {
                CustomUtils.errHandle(info.code, info.message, this)
            }
        }

    }

    private fun takeData(info: List<NextPeopleBean.Data.StaffRecord>) {
        people.clear()
        info.forEach {
            people.add(it.unitName + "  " + it.jobName + "  " + it.staffUserName)
        }
    }

    private fun showPeopleDialog() {

        MaterialDialog(this).show {
            title(R.string.select_next_reviewer)
            listItems(items = people) { _, index, text ->
                num = index
                nextList.add(info.data.staffRecordList[index])
                showPeople(text.toString())
            }

        }

    }

    private fun showPeople(str: String) {
        select_name_tv.text = str
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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}