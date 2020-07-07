package com.app.android.epro.epro.mvp.model.bean

data class DetailIntroductionBean(
    val `data`: Data,
    val message: String,
    val status: String,
    val code: Int
) {
    data class Data(
        val dataApproval: ApprovalInfoBean,
        val `object`: Object
    ) {
        data class Object(
            val introduceLetterId: String,
            val orgId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val introduceLetterType: String,//类别
            val introduceProjectId: String,
            val introduceProjectName: String,//项目名称
            val customerUnitId: String,
            val customerUnitName: String,//对方单位
            val mandataryUserId: String,
            val mandataryUserName: String,//被委托人名称
            val mandataryIdCard: String,//委托人身份证号码
            val mandataryUnitId: String,
            val mandataryUnitName: String,//申请单位
            val mandataryDepartmentId: String,
            val mandataryDepartmentName: String,//部门名称
            val introduceStartTime: String,//开始时间
            val introduceValidDay: Int,//有效期
            val introduceContent: String,//委托事项内容
            val approvalId: String,
            val approvalState: String,//审核状态（0默认状态，1待审核，2审核中，3已审批，4未通过，5退回，6已撤回，7已取消）
            val createUserPhone: String,
            val createUserName: String,
            val createTime: String,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        )
    }
}