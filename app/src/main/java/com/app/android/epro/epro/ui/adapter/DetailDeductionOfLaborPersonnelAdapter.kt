package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailDeductionOfLaborPersonnelBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class DetailDeductionOfLaborPersonnelAdapter(data: MutableList<DetailDeductionOfLaborPersonnelBean.Data.Object.DeductDetail>) :
    BaseQuickAdapter<DetailDeductionOfLaborPersonnelBean.Data.Object.DeductDetail, BaseViewHolder>(
        R.layout.item_deduction_labor_personne_list, data
    ) {


    override fun convert(
        holder: BaseViewHolder, item: DetailDeductionOfLaborPersonnelBean.Data.Object.DeductDetail
    ) {
        holder.setText(R.id.text1, item.workerUserName)
        holder.setText(R.id.text2, item.deductDateTime)
        holder.setText(R.id.text3, item.deductMoney.toString())
        holder.setText(R.id.text4, item.deductMoneyCapital)
        holder.setText(R.id.text5, item.deductReason)

    }
}