package com.app.android.epro.epro.mvp.model.bean

import java.math.BigDecimal

data class DetailDraftingOfContractBean(
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
            val contractCreationId: String,
            val orgName: String,
            val draftType: String,//起草方式（1备案项目 2直接起草）
            val contractProjectRecordId: String,
            val contractProjectRecprdName: String,
            val contractCode: String,
            val contractName: String,//合同名称
            val contractProvinceCode: String,
            val contractProvinceName: String,
            val contractCityCode: String,
            val contractCityName: String,//施工地址
            val partyUnitId: String,
            val partyName: String,//甲方名称
            val partyAdress: String,//甲方地址
            val partyLegalPerson: String,//甲方负责人
            val partyPhone: String,//甲方电话
            val partyEmail: String,//甲方邮箱
            val businessUserid: String,
            val businessUsername: String,//销售代表
            val prjectDepartmentName: String,//销售部门
            val prjectUserId: String,
            val prjectUserName: String,//项目经理姓名
            val contractInvoiceType: String,//发票类别(1普通发票2增值税专用发票)
            val contractBillingTax: String,//开票税率
            val contractBillingTaxName: String,
            val contractClassification: String,//合同项目分类（project_contract工程项目，debug_contract电力修试项目，design_contract设计咨询项目，epc_contract EPC项目）
            val contractType: String,
            val contractTypeName: String,//合同类型名称
            val contractIndustry: String,
            val contractIndustryName: String,//所属行业名称
            val contractMakeType: String,//订立形式（1网签，2书面合同）
            val signTime: String,//签订日期
            val contractEffectiveDate: String,//生效日期
            val contractTime: String,//合同工期
            val contractWarrantyDate: String,//合同质保时间（0无质保，1一年，2一年半，3两年，4两年半，5三年，6三年半，7四年，8四年半，9五年）
            val contractGuaranteeMoney: Double,//质保金比例（%）
            val contractAsType: String,//是否关联投标报价（1是）
            val contractTenderPrice: BigDecimal,//投标报价金额
            val contractAdvanceCharge: BigDecimal,//合同预付款
            val contractOriginalMoney: BigDecimal,//合同起始金额
            val contractFinalMoney: BigDecimal,//合同最终金额
            val contractMoneyCapital: String,//合同最终金额(大写)
            val contractTotalFee: Double,//成本百分比%
            val contractCost: BigDecimal,//预估成本
            val constraintCondition: String,//付款条件
            val contractSummary: String,//合同概况
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
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val projectAccountAmount: Double,
            val projectActualAmount: Double,
            val contractApportionAmount: Double,
            val contractAnnexOutDto: ContractAnnexOutDto,//合同附件对象
            val contractDetails: List<ContractDetail>,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>////上传图片或附件信息
        ) {
            data class ContractAnnexOutDto(
                val contractAnnexId: String,
                val contractId: String,
                val contractNumber: String,//主合同数量
                val contractScienceNumber: String,//合同技术协议数量
                val contractDrawNumber: String,//图纸数量
                val biddingNoticeNumber: String,//中标通知书数量
                val contractBudgetNumber: String,//合同预算书数量
                val tenderNumber: String,//投标书数量
                val packageNumber: String,//招标书数量
                val clarificationNumber: String,//投标澄清函数量
                val answerNumber: String,//投标答疑函数量
                val quoteNumber: String//投标报价书数量
            )

            data class ContractDetail(
                val contractDetailId: String,
                val detailContractId: String,
                val detailMaterialListId: String,
                val detailMaterialListName: String,
                val detailMaterialName: String,//物料名称
                val detailMaterialModel: String,//规格型号
                val detailMaterialUnits: String,//计量单位
                val detailMaterialNumber: Double,//数量
                val detailNotice: String,//备注
                val detailCreateUsername: String,
                val detailCreateTime: String,
                val detailLastUpdateUsername: String,
                val detailLastUpdateTime: String
            )

        }
    }
}