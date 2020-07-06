package com.app.android.epro.epro.ui.adapter


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.ApprovalInfoBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class ProcessInfoAdapter(data: MutableList<MutableList<ApprovalInfoBean.Statement>>) :
    BaseQuickAdapter<MutableList<ApprovalInfoBean.Statement>, BaseViewHolder>(
        R.layout.process_info, data
    ) {

    private var mRecyclerView: RecyclerView? = null
    var mAdapter: ProcessInfoSonAdapter? = null
    private var listen: ClickShowDialog? = null


    override fun convert(holder: BaseViewHolder, item: MutableList<ApprovalInfoBean.Statement>) {

        mRecyclerView = holder.getView(R.id.flow_info_list)
        setRecyclerView()

        mAdapter = ProcessInfoSonAdapter(item)
        mAdapter!!.setAnimationWithDefault(AnimationType.SlideInLeft)
        mRecyclerView!!.adapter = mAdapter

        mAdapter!!.setOnItemChildClickListener { _, _, position ->
            listen?.show(item[position])
        }

    }


    private fun setRecyclerView() {

        val linearLayoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        //mRecyclerView!!.isFocusable = false
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = linearLayoutManager
    }

    fun setClickListener(listener: ClickShowDialog) {
        this.listen = listener
    }


    interface ClickShowDialog {
        fun show(item: ApprovalInfoBean.Statement)
    }


}