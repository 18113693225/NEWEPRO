package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailDraftingOfContractBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class DetailDraftingOfContractAdapter(data: MutableList<DetailDraftingOfContractBean.Data.Object.ContractDetail>) :
    BaseQuickAdapter<DetailDraftingOfContractBean.Data.Object.ContractDetail, BaseViewHolder>(
        R.layout.item_drafting_of_contract_list, data
    ) {


    override fun convert(
        holder: BaseViewHolder, item: DetailDraftingOfContractBean.Data.Object.ContractDetail
    ) {
        holder.setText(R.id.text1, item.detailMaterialName)
        holder.setText(R.id.text2, item.detailMaterialModel)
        holder.setText(R.id.text3, item.detailMaterialUnits)
        holder.setText(R.id.text4, item.detailMaterialNumber.toString())
        holder.setText(R.id.text5, item.detailNotice)

    }
}