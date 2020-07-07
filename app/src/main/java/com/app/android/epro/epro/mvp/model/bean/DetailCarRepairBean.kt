package com.app.android.epro.epro.mvp.model.bean

data class DetailCarRepairBean(
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
            val vehicleMaintenanceId: String,
            val unitId: String,
            val unitName: String,//发起人单位名称
            val departmentId: String,
            val departmentName: String,//发起人部门名称
            val jobId: String,
            val jobName: String,//发起人职位名称
            val refenceCode: String,//业务编码
            val maintenanceType: String,//申请类型（1维修，2保养）
            val maintenanceVehicleId: String,
            val maintenanceVehicleName: String,//车牌号码
            val maintenanceStartTime: String,//开始时间
            val maintenanceEndTime: String,//结束时间
            val maintenanceUseType: String,//车辆使用类型（默认为2维修保养）统计
            val maintenanceVehicleMileage: Double,//已用公里数
            val maintenanceUnitName: String,//维修保养单位
            val maintenanceReason: String,//备注
            val approvalId: String,//审核单ID
            val approvalState: String,//审核状态（0默认状态，1待审核，2审核中，3已审批，4未通过，5退回，6已撤回）
            val createUserName: String,//创建人
            val createTime: String,//创建时间
            val allocationState: String,//维修状态（1正常，2维修保养申请中，3维修保养中
            val orgName: String,//公司名称
            val createUserPhone: String,//创建人电话
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        )
    }
}