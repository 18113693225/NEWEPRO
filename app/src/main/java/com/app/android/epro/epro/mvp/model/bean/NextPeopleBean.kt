package com.app.android.epro.epro.mvp.model.bean

data class NextPeopleBean(
    val `data`: Data,
    val message: String,
    val status: String,
    val code: Int
) {
    data class Data(
        val isSign: String,//下一步审批是否会签(1会签2单签)
        val isOperation: String,//下一步审批是否审批操作（1操作，2不操作）
        val operationId: String,//下一步审批操作id
        val isLastApproval: Boolean,//当前审批是否为最后一个审批人
        val staffRecordList: List<StaffRecord>//下一步审批人列表
    ) {
        data class StaffRecord(
            val staffUserId: String,//用户useId
            val staffUserName: String,//姓名
            val jobId: String,//职位ID
            val jobName: String,//职位名称
            val unitName: String,//单位名称
            val departmentName: String//部门名称
        )
    }
}