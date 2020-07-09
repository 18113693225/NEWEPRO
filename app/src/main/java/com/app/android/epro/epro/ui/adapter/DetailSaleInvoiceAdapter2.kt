package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailSalesInvoiceBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class DetailSaleInvoiceAdapter2(data: MutableList<DetailSalesInvoiceBean.Data.Object>) :
    BaseQuickAdapter<DetailSalesInvoiceBean.Data.Object, BaseViewHolder>(
        R.layout.item_sale_invoice_list2, data
    ) {


    override fun convert(
        holder: BaseViewHolder, item: DetailSalesInvoiceBean.Data.Object
    ) {
        holder.setText(R.id.text1, item.createTime)
        holder.setText(R.id.text2, item.applicationBuyName)
        holder.setText(R.id.text3, item.applicationSaleName)
        holder.setText(R.id.text4, item.applicationContractMoney.toString())
        holder.setText(R.id.text5, item.applicationThisInvoiced.toString())
        holder.setText(R.id.text6, item.applicationTaxInvoiced.toString())
        when (item.approvalState) {
            "1" -> holder.setText(R.id.text7, "待审核")
            "2" -> holder.setText(R.id.text7, "审核中")
            "3" -> holder.setText(R.id.text7, "已审批")
            "4" -> holder.setText(R.id.text7, "未通过")
            "5" -> holder.setText(R.id.text7, "退回")
            "6" -> holder.setText(R.id.text7, "已撤回")
            "7" -> holder.setText(R.id.text7, "已取消")

        }


    }
}