package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailProjectInitiationBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class DetailProjectInitiationAdapter(data: MutableList<DetailProjectInitiationBean.Data.Object.KeepOnStaff>) :
    BaseQuickAdapter<DetailProjectInitiationBean.Data.Object.KeepOnStaff, BaseViewHolder>(
        R.layout.item_project_initiation_list, data
    ) {


    override fun convert(
        holder: BaseViewHolder, item: DetailProjectInitiationBean.Data.Object.KeepOnStaff
    ) {
        holder.setText(R.id.text1, item.staffUserName)
        holder.setText(R.id.text2, item.staffUnitName)
        holder.setText(R.id.text3, item.staffDepartName)
        holder.setText(R.id.text4, item.staffJobName)
        holder.setText(R.id.text5, item.staffPhoneNumber)

    }


}