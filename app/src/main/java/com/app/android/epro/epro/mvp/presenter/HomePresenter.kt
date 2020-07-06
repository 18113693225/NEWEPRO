package com.app.android.epro.epro.mvp.presenter

import com.app.android.epro.epro.base.BasePresenter
import com.app.android.epro.epro.mvp.contract.HomeContract
import com.app.android.epro.epro.mvp.model.HomeModel
import com.app.android.epro.epro.net.exception.ExceptionHandle

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {


    private val homeModel: HomeModel by lazy {

        HomeModel()
    }


    override fun requestUserInfoData() {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeModel.getUserInfo().subscribe({ userInfoBean ->
            mRootView?.apply {
                dismissLoading()
                setUserInfoData(userInfoBean)
            }
        }, { t ->
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })
        addSubscription(disposable)
    }


}