package com.app.android.epro.epro.base

interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}