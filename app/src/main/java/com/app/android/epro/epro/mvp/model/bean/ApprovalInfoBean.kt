package com.app.android.epro.epro.mvp.model.bean


data class ApprovalInfoBean(
    val isFlower: Boolean,//是否在流程中
    val menuPageType: String,//菜单类型：1普通菜单，2请假，3审批金额
    val statements: List<List<Statement>>,//审批流水信息
    val sheet: Sheet,//审批单信息
    val messageList: List<Message>//询问信息列表
) {
    data class Statement(
        val approvalStatementsId: String,
        val flowSheetId: String,//审核单ID
        val shoudApprovalJobId: String,
        val shoudApprovalUserId: String,
        val shoudApprovalUserName: String,//下一步审核人名称
        val statementsFromJobId: String,
        val statementsFromUserid: String,
        val statementsFromUsername: String,//操作人用户名称(审批后，更新)
        val statementsNowStep: Int,//现在审核步骤
        val statementsState: String,//状态(0待审核，1，通过，2，未通过，3，已审，4拒绝)
        val statementsNote: String,//送审说明/审核意见
        val statementsApprovalTime: String,//操作时间
        val statementsIsValid: String,//是否有效（0无效，1有效）
        val statementsFlowState: String,//流程状态（1待审核，2审核中，3已审批，4未通过，5退回）
        val statementsApprovalValue: Any,//审批金额
        val headPortraitUrl: String//审批人头像url
    )

    data class Sheet(
        val flowApprovalSheetId: String,
        val orgId: String,
        val unitId: String,
        val unitName: String,//单位名称
        val departmentId: String,
        val departmentName: String,//部门名称
        val jobId: String,
        val jobName: String,//职务名称
        val flowMenuCode: String,//菜单编码
        val flowMenuType: String,//菜单名称
        val flowApprovalSetId: String,
        val flowReferenceId: String,//关联详情单子ID
        val flowRefenceCode: String,//业务编号
        val flowTitle: String,//审核单标题
        val flowTotalStep: Int,//流程总步骤
        val flowStartStep: Int,//流程起始步数
        val flowStopValue: Any,//送审金额/天数
        val flowStopStep: Int,//流程结束步数
        val flowNowStep: Int,//流程现在走到的步骤
        val flowIsUrgent: Int,//是否紧急（0不是，1是 ）
        val flowFreezeState: String,//流程状态（1正常，2冻结中,3撤回）
        val flowFreezeUserId: Any,
        val flowFreezeUserName: Any,//冻结人名称
        val flowFreezeTime: Any,//冻结时间
        val flowFreezeNotice: Any,//冻结事由
        val flowState: String,//审核状态（1待审核，2审核中，3已审批，4未通过，5退回）
        val flowBackNotice: Any,//撤回原因
        val flowSendTimes: Int,//流程发起次数
        val refenceIsValid: String,//是否有效（0无效，1有效）
        val createUserId: String,
        val createUserName: String,//创建人
        val createTime: String,//创建时间
        val updateUserId: Any,
        val updateUserName: Any,//修改人
        val updateTime: Any,//修改时间
        val headPortraitUrl: String,//头像URL
        val supervisedViewId: Any
    )

    data class Message(
        val approvalMessageId: String,
        val messMenuCode: String,//菜单编码
        val messReferenceId: String,
        val messType: String,//是否会签(1会签2单签)
        val messCreateUid: String,
        val messCreateUname: String,//发送人名称
        val messReplyUid: String,
        val messReplyUname: String,//回复对方名称
        val messContent: String,//消息内容
        val messCreateTime: String,//发消息时间
        val messReturnTime: String,//回复时间
        val messIsValid: String,//是否有效（0无效，1有效）
        val askHeadPortraitUrl: String,//询问人头像URL
        val replyHeadPortraitUrl: String//回复人头像URL
    )
}



