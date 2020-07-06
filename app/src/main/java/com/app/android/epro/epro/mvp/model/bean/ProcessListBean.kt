package com.app.android.epro.epro.mvp.model.bean

data class ProcessListBean(
    val `data`: Data,
    val message: String,
    val status: String,
    val code: Int
) {
    data class Data(
        val total: Int,
        val pages: Int,
        val list: ArrayList<ProcessData>
    ) {
        data class ProcessData(
            val flowMenuCode: String,//菜单编码
            val flowMenuType: String,//菜单名称
            val flowApprovalSetId: String,
            val flowReferenceId: String,//关联详情单子ID
            val flowTitle: String,//审核单标题
            val flowIsUrgent: Int,//是否紧急（0不是，1是
            val flowFreezeState: String,//流程状态（1正常，2冻结中,3撤回）
            val flowState: String,//审核状态（1待审核，2审核中，3已审批，4未通过，5退回）
            val createUserName: String,//创建人
            val createTime: String,//创建时间
            val jobId: String //职务id/应审人jobId
        )
    }
}