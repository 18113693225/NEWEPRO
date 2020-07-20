package com.app.android.epro.epro.mvp.model.bean

import java.math.BigDecimal

data class DetailDeductionOfLaborPersonnelBean(
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
            val workerDeductId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val workerPlanId: String,
            val planDepartmentId: String,
            val planDepartmentName: String,
            val workerDeductType: String,//劳务人员类型（1临时，2分包劳务）
            val workerContractId: String,
            val workerContractName: String,//销售合同名称
            val workerProjectId: String,
            val workerProjectName: String,
            val workerProjectType: String,
            val workerProjectManagerName: String,//项目经理名称
            val workerDeductRemark: String,//备注
            val approvalId: String,
            val approvalState: String,
            val createUserPhone: String,
            val createUserName: String,
            val createTime: String,
            val updateUserName: String,
            val updateTime: String,
            val deductDetailList: List<DeductDetail>,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>////上传图片或附件信息
        ) {
            data class DeductDetail(
                val workerDeductDetailId: String,
                val workerDeductId: String,
                val workerReocrdMiddlerId: String,
                val workerUserName: String,//劳务人员名称
                val deductDateTime: String,//扣款日期
                val deductMoney: BigDecimal,//扣款金额
                val deductMoneyCapital: String,//大写
                val deductReason: String,//扣款理由
                val refenceIsValid: String,
                val createTime: String
            )
        }
    }
}