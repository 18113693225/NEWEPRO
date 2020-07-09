package com.app.android.epro.epro.mvp.model.bean

data class DetailFormProjectPersonnelBean(
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
            val itemPeopleBuildId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val buildProjectId: String,
            val buildProjectName: String,//项目名称
            val buildPlanId: String,
            val buildProjectManagerId: String,
            val buildProjectManagerName: String,//项目经理名称
            val planDepartmentId: String,
            val planDepartmentName: String,
            val projectStartTime: String,//项目开始时间
            val projectEndTime: String,//项目结束时间
            val projectAddress: String,//项目地址
            val peopleBuildNumber: Int,//项目人数
            val peopleBuildRemark: String,//备注
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
            val itemPeopleList: List<ItemPeople>,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        ) {
            data class ItemPeople(
                val itemPeopleId: String,
                val peopleBuildId: String,
                val itemPeopleUserId: String,
                val itemPeopleUserName: String,//项目人员姓名
                val itemPeopleProjectId: String,
                val itemPeopleProjectName: String,
                val itemPeoplePlanId: String,
                val itemPeopleJobName: String,//岗位名称
                val itemPeopleTouchPerson: String,
                val itemPeopleDispatchTime: String,
                val itemPeopleType: String,
                val itemPeopleDispatchState: String,
                val itemPeopleStartTime: String,
                val itemPeopleEndTime: String,
                val dutyType: String//项目人员职责：worker职员，manager项目经理，oldManager原项目经理
            )

        }
    }
}