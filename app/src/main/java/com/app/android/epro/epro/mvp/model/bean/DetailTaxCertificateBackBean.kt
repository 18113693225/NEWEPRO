package com.app.android.epro.epro.mvp.model.bean

data class DetailTaxCertificateBackBean(
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
            val taxCertificateBackId: String,
            val orgId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val taxOutId: String,
            val backProjectId: String,
            val backProjectName: String,//项目名称
            val backPartyName: String,//甲方名称
            val backApplyUserId: String,
            val backApplyUserName: String,//申请人名称
            val backApplyUnitId: String,
            val backApplyUnitName: String,//申请人所属单位名称
            val backApplyDepartmentId: String,
            val backApplyDepartmentName: String,//申请人所属部门名称
            val backNotice: String,//备注
            val approvalId: String,
            val approvalState: String,
            val createUserPhone: String,
            val createUserName: String,
            val createTime: String,
            val updateUserName: String,
            val updateTime: String,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        )
    }
}