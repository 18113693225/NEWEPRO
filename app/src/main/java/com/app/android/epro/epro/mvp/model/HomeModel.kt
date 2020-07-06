package com.app.android.epro.epro.mvp.model

import com.app.android.epro.epro.mvp.model.bean.UserInfoBean
import com.app.android.epro.epro.net.RetrofitManager
import com.app.android.epro.epro.rx.scheduler.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

class HomeModel {

    /**
     * 获取用户数据
     */
    fun getUserInfo(): Observable<UserInfoBean> {
        return RetrofitManager.service.getUserInfoData()
            .compose(SchedulerUtils.ioToMain())
    }

}