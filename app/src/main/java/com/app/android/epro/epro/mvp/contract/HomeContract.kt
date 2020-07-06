package com.app.android.epro.epro.mvp.contract

import com.app.android.epro.epro.base.IBaseView
import com.app.android.epro.epro.mvp.model.bean.UserInfoBean

interface HomeContract {


    interface View : IBaseView {

        /**
         * 设置用户的数据
         */
        fun setUserInfoData(userInfoBean: UserInfoBean)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)


    }

    interface Presenter {

        /**
         * 获取用户
         */
        fun requestUserInfoData()


    }


}