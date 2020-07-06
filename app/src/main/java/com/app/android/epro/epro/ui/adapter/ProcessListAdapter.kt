package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.ProcessListBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ProcessListAdapter(data: MutableList<ProcessListBean.Data.ProcessData>) :
    BaseQuickAdapter<ProcessListBean.Data.ProcessData, BaseViewHolder>(
        R.layout.item_process_list, data
    ) {
    override fun convert(holder: BaseViewHolder, item: ProcessListBean.Data.ProcessData) {
        holder.setText(R.id.type_tv, item.flowMenuType)
        holder.setText(R.id.content_tv, item.flowTitle)
        holder.setText(R.id.name_tv, item.createUserName)
        holder.setText(R.id.end_time_tv, "时间:" + item.createTime)
        when (item.flowState) {
            "1" -> {
                holder.setText(R.id.status_tv, R.string.To_be_reviewed)
                holder.setBackgroundResource(R.id.status_tv, R.drawable.shape_bg_approve_wait)
            }
            "2" -> {
                holder.setText(R.id.status_tv, R.string.Under_review)
                holder.setBackgroundResource(R.id.status_tv, R.drawable.shape_bg_approve_ing)
            }
            "3" -> {
                holder.setText(R.id.status_tv, R.string.Approved)
                holder.setBackgroundResource(R.id.status_tv, R.drawable.shape_bg_approve_yes)
            }
            "4" -> {
                holder.setText(R.id.status_tv, R.string.Fail)
                holder.setBackgroundResource(R.id.status_tv, R.drawable.shape_bg_approve_no)
            }
            "5" -> {
                holder.setText(R.id.status_tv, R.string.Back)
                holder.setBackgroundResource(R.id.status_tv, R.drawable.shape_bg_approve_no)
            }
        }

        if (item.flowFreezeState == "2") {
            holder.setVisible(R.id.freeze_state_tv, true)
        } else {
            holder.setVisible(R.id.freeze_state_tv, false)
        }
    }

}