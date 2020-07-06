package com.app.android.epro.epro.ui.adapter

import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.ApprovalInfoBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class ProcessMessageAdapter(data: MutableList<ApprovalInfoBean.Message>) :
    BaseQuickAdapter<ApprovalInfoBean.Message, BaseViewHolder>(
        R.layout.process_message, data
    ) {

    override fun convert(holder: BaseViewHolder, item: ApprovalInfoBean.Message) {
        holder.setText(R.id.name1, item.messCreateUname)
        holder.setText(R.id.name2, item.messReplyUname)
        holder.setText(R.id.time, item.messCreateTime)
        holder.setText(R.id.content, item.messContent)


    }


}