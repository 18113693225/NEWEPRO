package com.app.android.epro.epro.mvp.presenter

import com.app.android.epro.epro.base.BasePresenter
import com.app.android.epro.epro.mvp.contract.LoginContract
import com.app.android.epro.epro.mvp.model.LoginModel
import com.app.android.epro.epro.mvp.model.bean.LoginBean
import com.app.android.epro.epro.net.exception.ExceptionHandle
import okhttp3.RequestBody
import retrofit2.http.Body

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {


    private val loginModel: LoginModel by lazy {

        LoginModel()
    }


    override fun requestLoginData(body: RequestBody) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = loginModel.login(body).subscribe({ loginBean ->
            mRootView?.apply {
                dismissLoading()
                setLoginData(loginBean)
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