package com.app.android.epro.epro.mvp.model

import com.app.android.epro.epro.mvp.model.bean.ProcessListBean
import com.app.android.epro.epro.net.RetrofitManager
import com.app.android.epro.epro.rx.scheduler.SchedulerUtils
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody

class ProcessListModel {

    /**
     * 获取审核列表数据
     */
    fun getApproveList(body: RequestBody): Observable<ProcessListBean> {
        return RetrofitManager.service.getApproveListData(body)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取询问 退回 监督列表
     */
    fun getMoreList(body: RequestBody): Observable<ProcessListBean> {
        return RetrofitManager.service.getMoreListData(body)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取待办列表
     */
    fun getDetailList(map: HashMap<String, String>): Observable<ProcessListBean> {
        return RetrofitManager.service.getDetailListData(map)
            .compose(SchedulerUtils.ioToMain())
    }


}