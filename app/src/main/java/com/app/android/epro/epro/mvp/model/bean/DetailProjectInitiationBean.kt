package com.app.android.epro.epro.mvp.model.bean

import java.math.BigDecimal

data class DetailProjectInitiationBean(
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
            val projectKeepOnRecordId: String,
            val orgId: String,
            val recordProjectName: String,//项目名称
            val customerUnitId: String,
            val customerUnitName: String,//客户单位名称
            val customerChuanzhen: String,
            val partyAdress: String,
            val partyLeading: String,
            val partyLeadingPhone: String,
            val partyTell: String,
            val partyEmail: String,
            val projectRecordIndustry: String,
            val projectRecordIndustryName: String,//所属行业名称
            val recordNature: String,
            val recordNatureName: String,//项目性质名称
            val projectType: String,
            val projectTypeName: String,//项目类别名称
            val projectPlayStarttime: String,//计划启动日期
            val projectPlayEndtime: String,//计划骏工日期
            val projectForecastContracttime: String,//预计签约日期
            val projectTotalInvestment: BigDecimal,//项目总投资
            val forecastContracttimeMoney: BigDecimal,//预计合同金额
            val forecastBusinessMoney: BigDecimal,//预计业务费用
            val recordUpperMoney: String,//总投资大写金额
            val projectResponsibleUserid: String,
            val projectResponsibleUsername: String,//销售经理名称
            val responsibleProsonOrgName: String,//销售经理所属部门名称
            val responsibleProsonDeptName: String,//销售经理单位名称
            val responsiblePresonPhone: String,//销售经理手机
            val recordIsWinTheBidding: String,//是否中标（ 1中标，2未中标）
            val projectContent: String,//项目概况
            val recordPartyCommond: String,//甲方项目总指挥（负责人）
            val recordPartyCommondDepart: String,//甲方项目总指挥（负责人）部门
            val recordPartyCommondJob: String,//甲方项目总指挥（负责人）职务
            val recordPartyCommondTel: String,//甲方项目总指挥（负责人）电话
            val recordManagerUsername: String,//甲方技术负责人
            val recordManagerDeptName: String,//甲方技术负责人部门
            val recordManagerJobName: String,//甲方技术负责人职务
            val recordMangerTel: String,//甲方技术负责人电话
            val recordPartyLive: String,//甲方现场代表
            val recordPartyLiveDepart: String,//甲方现场代表部门
            val recordPartyLiveJob: String,//甲方现场代表职务
            val recordPartyLiveTel: String,//甲方现场代表电话
            val customerUnitAdress: String,//甲方地址
            val responsibleProsonTell: String,//办公电话
            val responsiblePresonEmail: String,//Email
            val designUnit: String,//设计单位
            val mainDesignUsername: String,//主设计人
            val mainDesignPhone: String,//主设计人电话
            val tenderUnit: String,//招标单位
            val supervisionUnit: String,//监理单位
            val tenderType: String,//招标类型
            val keepOnFlag: String,//立项备案类型标识：0立项备案 1设计咨询立项备案
            val refenceCode: String,
            val approvalId: String,
            val approvalState: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val createTime: String,
            val createUserName: String,
            val updateUserName: String,
            val updateTime: String,
            val recordsType: String,//1内部备案，2外部备案
            val recordsRemark: String,//立项申请说明
            val orgName: String,
            val createUserPhone: String,
            val changeKeepRecordsId: String,
            val keepOnStaffList: List<KeepOnStaff>,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        ) {
            data class KeepOnStaff(
                val projectKeepOnStaffId: String,
                val staffUnitId: String,
                val staffUnitName: String,//单位名称
                val staffDepartId: String,
                val staffDepartName: String,//部门名称
                val staffJobId: String,
                val staffJobName: String,//职位名称
                val staffUserId: String,
                val staffUserName: String,//人员姓名
                val staffPhoneNumber: String,//电话
                val staffProjectKeepId: String,
                val staffChangeType: String
            )
        }
    }
}