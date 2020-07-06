package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.ApprovalInfoBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ProcessInfoSonAdapter(data: MutableList<ApprovalInfoBean.Statement>) :
    BaseQuickAdapter<ApprovalInfoBean.Statement, BaseViewHolder>(
        R.layout.process_info_son, data
    ) {
    init {
        addChildClickViewIds(R.id.msg_bt)
    }


    override fun convert(holder: BaseViewHolder, item: ApprovalInfoBean.Statement) {

        if (item.statementsNowStep == 1) {
            holder.setText(R.id.name, item.statementsFromUsername)
        } else {
            holder.setText(R.id.name, item.shoudApprovalUserName)
        }
        when (item.statementsState) {
            "0" -> {
                holder.setText(R.id.status, "待审核")
                holder.setBackgroundResource(R.id.status, R.drawable.shape_bg_approve_wait)
            }
            "1" -> {
                holder.setText(R.id.status, "通过")
                holder.setBackgroundResource(R.id.status, R.drawable.shape_bg_approve_yes)
            }
            "2" -> {
                holder.setText(R.id.status, "未通过")
                holder.setBackgroundResource(R.id.status, R.drawable.shape_bg_approve_no)
            }
            "3" -> {
                holder.setText(R.id.status, "已处理")
                holder.setBackgroundResource(R.id.status, R.drawable.shape_bg_approve_yes)
            }
            "4" -> {
                holder.setText(R.id.status, "退回")
                holder.setBackgroundResource(R.id.status, R.drawable.shape_bg_approve_no)
            }
        }
        holder.setText(R.id.time, item.statementsApprovalTime)
        holder.setText(R.id.content, item.statementsNote)

    }


}