package com.app.android.epro.epro.mvp.model

import com.app.android.epro.epro.mvp.model.bean.LoginBean
import com.app.android.epro.epro.net.RetrofitManager
import com.app.android.epro.epro.rx.scheduler.SchedulerUtils
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody


class LoginModel {

    /**
     * 获取登陆数据
     */
    fun login(body: RequestBody): Observable<LoginBean> {
        return RetrofitManager.service.getLoginData(body)
            .compose(SchedulerUtils.ioToMain())
    }


}