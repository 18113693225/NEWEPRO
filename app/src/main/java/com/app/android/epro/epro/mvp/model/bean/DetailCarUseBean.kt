package com.app.android.epro.epro.mvp.model.bean

data class DetailCarUseBean(
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
            val vehicleApplicationId: String,
            val unitId: String,
            val unitName: String,
            val departmentId: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val applicationState: String,
            val applicationReason: String,//用车事由
            val applicationVehicleId: String,
            val applicationVehicleName: String,//车牌号码
            val applicationAdress: String,//目的地
            val applicationStartTime: String,//出发时间
            val applicationEndTime: String,//用车结束时间
            val applicationBrand: String,//车辆品牌
            val applicationType: String,//申请车辆类型（1轿车，2SUV, 3,工程车，4商务车，5客车，6货车，7MPV，8跑车）
            val approvalId: String,
            val approvalState: String,
            val createUserName: String,
            val createTime: String,
            val orgName: String,
            val createUserPhone: String,
            val extendData: Any,
            val annexItems: List<AnnexItem>//上传图片或附件信息
        )
    }
}