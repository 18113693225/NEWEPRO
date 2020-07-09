package com.app.android.epro.epro.mvp.model.bean

data class DetailProductionTaskPlanBean(
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
            val engineeringTaskPlanningId: String,
            val orgName: String,
            val changeEngineId: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val engineContractId: String,
            val engineContractName: String,//销售合同名称/项目名称
            val engineType: String,//合同项目分类（project_contract工程项目，debug_contract电力修试项目，design_contract设计咨询项目，epc_contract EPC项目)
            val engineBusinessOwnerId: String,
            val engineBusinessOwnerName: String,
            val enginePartyName: String,//甲方名称
            val enginePartyLinkman: String,//甲方联系人
            val enginePartyPhone: String,//甲方联系电话
            val designUnit: String,//设计单位
            val designLinkMan: String,//设计联系
            val designPhone: String,//设计电话
            val supervisorUnit: String,//监理单位
            val supervisorLinkMan: String,//监理联系人
            val supervisorPhone: String,//监理联系电话
            val engineAdress: String,//工程地址
            val engineLongitude: String,
            val engineLatitude: String,
            val engineScope: Double,//施工范围(单位：米)
            val cuttingValue: Double,//打卡相似度
            val engineStartTime: String,//开工日期
            val engineTimeLimit: String,//合同工期
            val engineBusinessOwnerPhone: String,//负责人电话
            val allocatingTaskDepartmentId: String,
            val allocatingTaskDepartmentName: String,//实施部门名称
            val transferProjectInformation: String,//项目资料
            val engineTaskContent: String,//任务内容
            val explanationBusiness: String,
            val contractOperateUnit: String,
            val technicalDirector: String,
            val jijingDisclose: String,
            val engineInitialAmount: Double,
            val engineTaxes: Double,
            val engineTotalAmount: Double,
            val engineFinalAmount: Double,
            val engineFinalAmountCapital: String,
            val refenceCode: String,
            val approvalId: String,
            val approvalState: String,
            val approvalStartTime: String,
            val approvalPassTime: String,
            val approvalCancelStartTime: String,
            val approvalCancelPassTime: String,
            val cancelState: String,
            val cancelReferenceId: String,
            val createUserName: String,
            val createUserPhone: String,
            val createTime: String,
            val updateUserName: String,
            val updateTime: String,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        )
    }
}