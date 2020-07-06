package com.app.android.epro.epro.mvp.model.bean

data class SendApprovalInfo(
    val approvalContent: String,//审批内容
    val approvalFrom: String,//来源（1web端，2，APP端）
    val approvalValue: Int?,//审批条件：金额 ，请假天数
    val flowApprovalSheetId: String,//审批单id
    val approvalJobId: String,//应审人jobId
    val userList: List<NextPeopleBean.Data.StaffRecord>?//下一步审核人信息
)