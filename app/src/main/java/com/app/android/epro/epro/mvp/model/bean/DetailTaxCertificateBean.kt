package com.app.android.epro.epro.mvp.model.bean

import java.math.BigDecimal

data class DetailTaxCertificateBean(
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
            val taxCertificateOutId: String,
            val orgId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val taxProjectId: String,
            val taxProjectName: String,//项目名称
            val taxPartyName: String,//甲方名称
            val taxApplyUserId: String,
            val taxApplyUserName: String,//申请人名称
            val taxApplyUnitId: String,
            val taxApplyUnitName: String,//申请人所属单位名称
            val taxApplyDepartmentId: String,
            val taxApplyDepartmentName: String,//申请人所属部门名称
            val taxApplicantMoney: BigDecimal,//开票金额
            val taxUpperMoney: String,//大写金额
            val taxNotice: String,//备注
            val approvalId: String,
            val approvalState: String,
            val createUserPhone: String,
            val createUserName: String,
            val createTime: String,
            val updateUserName: String,
            val updateTime: String,
            val taxBackTime: String,
            val taxBackUserName: String,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        )
    }
}