package com.app.android.epro.epro.mvp.contract

import com.app.android.epro.epro.base.IBaseView
import com.app.android.epro.epro.mvp.model.bean.ProcessListBean
import okhttp3.RequestBody

interface ProcessListContract {


    interface View : IBaseView {


        /**
         * 设置流程列表信息
         */
        fun setProcessListData(data: ProcessListBean)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        /**
         * 获取流程审核列表数据
         */
        fun requestApproveListData(body: RequestBody)

        /**
         * 获取询问 退回 监督列表
         */
        fun requestMoreListData(body: RequestBody)

        /**
         * 获取待办列表
         */
        fun requestDetailListData(map: HashMap<String, String>)
    }
}