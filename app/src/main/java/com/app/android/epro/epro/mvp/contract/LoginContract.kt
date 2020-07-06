package com.app.android.epro.epro.mvp.contract

import com.app.android.epro.epro.base.IBaseView
import com.app.android.epro.epro.mvp.model.bean.LoginBean
import okhttp3.RequestBody
import retrofit2.http.Body

interface LoginContract {


    interface View : IBaseView {

        /**
         * 登陆
         */
        fun setLoginData(loginBean: LoginBean)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)


    }

    interface Presenter {

        /**
         * 获取登陆数据
         */
        fun requestLoginData(body: RequestBody)


    }


}