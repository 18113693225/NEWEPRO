package com.app.android.epro.epro.mvp.model.bean

import java.math.BigDecimal

data class DetailFormProjectLaborPersonnelBean(
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
            val workerRecordBuildId: String,
            val orgId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val recordId: String,
            val workerPlanId: String,
            val workerContractId: String,
            val workerContractName: String,//销售合同名称
            val planDepartmentId: String,
            val planDepartmentName: String,
            val workerProjectType: String,
            val workerProjectId: String,
            val workerProjectName: String,//分包合同名称
            val workerProjectManagerName: String,//项目经理名称
            val workerRecordType: String,//劳务类型(1临时，2分包劳务)
            val workerUserName: String,//姓名
            val workerSex: String,//性别（1男，2女）
            val workerIdCard: String,//身份证号码
            val workerPhone: String,//联系电话
            val workerAddress: String,//家庭住址
            val workerJobType: String,//工种
            val workerClass: String,//班组
            val workerJobState: String,//职务状态
            val workerBankName: String,//开户行
            val workerBankNumber: String,//卡号
            val workerDateMoney: BigDecimal,//日薪资
            val workerEmergencyName: String,//应急联系人
            val worlerEmergencyPhone: String,//应急联系人电话
            val workerEntryTime: String,//入场时间
            val workerLeaveTime: String,//预计离场时间
            val workerIsFace: String,//人脸实名认证：1已实名认证，2未实名认证
            val workerFaceUrl: String,//人脸图片URL
            val workerCardUrl: String,//身份证URL
            val approvalId: String,
            val approvalState: String,
            val approvalPassTime: String,
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