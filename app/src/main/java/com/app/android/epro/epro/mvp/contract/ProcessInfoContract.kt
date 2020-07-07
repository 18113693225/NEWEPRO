package com.app.android.epro.epro.mvp.contract

import com.app.android.epro.epro.base.IBaseView
import okhttp3.RequestBody


interface ProcessInfoContract {

    interface View : IBaseView {


        /**
         * 设置业务单详情信息
         */
        fun setDetailInfoData(data: Any)

        /**
         * 设置审批信息反馈
         */
        fun setDetailBackData(data: Any)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        /**
         * 获取业务单详情数据
         */
        fun requestDetailInfoData(
            recordId: String,
            menuCode: String
        )

        /**
         * 审批不通过/退回
         */
        fun goBack(
            approvalContent: String,
            flowApprovalSheetId: String
        )

        /**
         * 审批通过
         */

        fun pass(body: RequestBody)

        /**
         * 审批冻结
         */
        fun freeze(flowApprovalSheetId: String, flowFreezeState: String)

        /**
         * 撤回询问
         */
        fun recallMsg(messageId: String)

        /**
         * 保存询问
         */
        fun keepMsg(body: RequestBody)

        /**
         * 查询下一步审核人
         */
        fun nextPeople(flowApprovalSheetId: String)

        /**
         * 消息总和（流程）
         */
        fun infoNum()


    }

}