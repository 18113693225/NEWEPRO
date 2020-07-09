package com.app.android.epro.epro.mvp.model.bean

import java.math.BigDecimal

data class DetailSalesInvoiceBean(
    val `data`: Data,
    val message: String,
    val status: String,
    val code: Int
) {
    data class Data(
        val taxInvoicedTotal: BigDecimal,
        val thisInvoicedTotal: BigDecimal,
        val invoicedTotalList: List<Object>,
        val dataApproval: ApprovalInfoBean,
        val `object`: Object
    ) {
        data class Object(
            val applicationFormId: String,
            val orgId: String,
            val orgName: String,
            val unitName: String,
            val departmentName: String,
            val jobId: String,
            val jobName: String,
            val refenceCode: String,
            val applicationContractId: String,
            val applicationContractName: String,//合同名称
            val applicationContractMoney: BigDecimal,//合同金额
            val applicationHaveMoney: BigDecimal,//已收款金额
            val applicationHaveInvoiced: BigDecimal,//已开票金额
            val applicationSurplusInvoiced: BigDecimal,//可开票金额
            val applicationThisInvoiced: BigDecimal,//本次开票金额
            val applicationTaxInvoiced: BigDecimal,//税额
            val applicationQueryInvoiced: Any,
            val applicationUpperInvoiced: String,//本次开票金额大写
            val applicationInvoiceType: String,//发票类型:1增值税专用发票，2增值税普通发票
            val applicationUserId: String,
            val applicationUserName: String,//申请人名称
            val applicationSendAdress: String,//寄送地址
            val applicationSendType: String,//邮寄方式1快递，2领票人送票，3对方上门取票
            val applicationNotice: String,//备注
            val applicationBuyName: String,//购买方名称
            val applicationBuyTax: String,//购买方纳税人识别号
            val applicationBuyAddress: String,//购买方地址
            val applicationBuyBank: String,//购买方开户行及账号
            val applicationSaleName: String,//销售方名称
            val applicationSaleTax: String,//销售方纳税人识别号
            val applicationSaleAddress: String,//销售方地址
            val applicationSaleBank: String,//销售方开户行及账号
            val approvalId: String,
            val approvalState: String,//审核状态（0默认状态，1待审核，2审核中，3已审批，4未通过，5退回，6已撤回，7已取消）
            val cancelState: String,
            val cancelRefenceId: Any,
            val createUserPhone: String,
            val createUserName: String,
            val createTime: String,
            val updateUserName: Any,
            val updateTime: Any,
            val formDetailsList: List<FormDetails>,
            val extendData: ExtendData,//自定义表单
            val annexItems: List<AnnexItem>//上传图片或附件信息
        ) {
            data class FormDetails(
                val detailsId: String,
                val detailsContentId: String,
                val detailsContent: String,//货物或者商品名称
                val detailsSpecification: String,//规格
                val detailsUnit: String,//单位
                val detailsNum: Double,//数量
                val detailsPrice: BigDecimal,//单价（含税）
                val detailsMoney: BigDecimal,//金额（含税)
                val detailsTaxRate: String,//税率
                val detailsTax: BigDecimal,//税额
                val detailsApplicationId: String
            )

        }
    }
}