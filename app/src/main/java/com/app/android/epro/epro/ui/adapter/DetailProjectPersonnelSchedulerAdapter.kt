package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.DetailProjectPersonnelSchedulerBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class DetailProjectPersonnelSchedulerAdapter(data: MutableList<DetailProjectPersonnelSchedulerBean.Data.Object.ItemDispatchDetail>) :
    BaseQuickAdapter<DetailProjectPersonnelSchedulerBean.Data.Object.ItemDispatchDetail, BaseViewHolder>(
        R.layout.item_project_personnel_scheduler_list, data
    ) {


    override fun convert(
        holder: BaseViewHolder,
        item: DetailProjectPersonnelSchedulerBean.Data.Object.ItemDispatchDetail
    ) {
        when (item.dispatchType) {
            "1" -> holder.setText(R.id.text1, "调回公司")
            "2" -> holder.setText(R.id.text1, "调往项目")
        }
        holder.setText(R.id.text2, item.dispatchUserName)
        when (item.planDispatchType) {
            "1" -> {
                holder.setText(R.id.text3, item.dispatchOldProjectName)
                holder.setText(R.id.text4, item.dispatchProjectName)
            }
            "2" -> {
                holder.setText(R.id.text3, item.dispatchOldProjectName)
                holder.setText(R.id.text4, "调回公司")
            }
            "3" -> {
                holder.setText(R.id.text3, "公司调项目")
                holder.setText(R.id.text4, item.dispatchProjectName)
            }

        }

        holder.setText(R.id.text5, item.dispatchReportTime)
        holder.setText(R.id.text6, item.dispatchTouchPerson)

    }
}