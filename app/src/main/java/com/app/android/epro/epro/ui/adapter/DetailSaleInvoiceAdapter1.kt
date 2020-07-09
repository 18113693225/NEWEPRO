package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailSalesInvoiceBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class DetailSaleInvoiceAdapter1(data: MutableList<DetailSalesInvoiceBean.Data.Object.FormDetails>) :
    BaseQuickAdapter<DetailSalesInvoiceBean.Data.Object.FormDetails, BaseViewHolder>(
        R.layout.item_sale_invoice_list1, data
    ) {


    override fun convert(
        holder: BaseViewHolder, item: DetailSalesInvoiceBean.Data.Object.FormDetails
    ) {
        holder.setText(R.id.text1, item.detailsContent)
        holder.setText(R.id.text2, item.detailsSpecification)
        holder.setText(R.id.text3, item.detailsUnit)
        holder.setText(R.id.text4, item.detailsNum.toString())
        holder.setText(R.id.text5, item.detailsPrice.toString())
        holder.setText(R.id.text6, item.detailsMoney.toString())
        holder.setText(R.id.text7, item.detailsTaxRate)
        holder.setText(R.id.text8, item.detailsTax.toString())

    }
}