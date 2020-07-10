package com.app.android.epro.epro.mvp.model.bean

data class DetailProjectPersonnelSchedulerBean(
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
            val itemPeopleDispatchId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val dispatchRemark: String,//备注
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
            val itemDispatchDetailList: List<ItemDispatchDetail>,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        ) {
            data class ItemDispatchDetail(
                val itemDispatchDetailId: String,
                val peopleDispatchId: String,
                val dispatchUserId: String,
                val dispatchUserName: String,//调度人员姓名
                val dispatchType: String,//调度类型（1调回公司，2调往项目）
                val dispatchOldPlanId: String,
                val dispatchOldProjectId: String,
                val dispatchOldProjectName: String,//老项目名称（公司调往项目时为空）
                val dispatchBuildId: String,
                val dispatchPlanId: String,
                val dispatchProjectId: String,
                val dispatchProjectName: String,//调往项目名称（项目调回公司时为空）
                val planDepartmentId: String,
                val planDepartmentName: String,
                val dispatchAddress: String,
                val dispatchReportTime: String,//报到时间
                val dispatchTouchPerson: String,//报到联系人
                val planDispatchType: String//调回往类型(1项目调项目,2项目调公司,3公司调项目)
            )
        }
    }
}