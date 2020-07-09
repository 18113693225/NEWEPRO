package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailFormProjectPersonnelBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class DetailFormProjectPersonnelAdapter(data: MutableList<DetailFormProjectPersonnelBean.Data.Object.ItemPeople>) :
    BaseQuickAdapter<DetailFormProjectPersonnelBean.Data.Object.ItemPeople, BaseViewHolder>(
        R.layout.item_form_project_personnel_list, data
    ) {


    override fun convert(
        holder: BaseViewHolder, item: DetailFormProjectPersonnelBean.Data.Object.ItemPeople
    ) {
        holder.setText(R.id.text1, item.itemPeopleUserName)
        holder.setText(R.id.text2, item.itemPeopleJobName)
        when (item.dutyType) {
            "worker" -> holder.setText(R.id.text3, "职员")
            "manager" -> holder.setText(R.id.text3, "项目经理")
            "oldManager" -> holder.setText(R.id.text3, "原项目经理")
        }


    }
}