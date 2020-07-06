package com.app.android.epro.epro.mvp.presenter

import com.app.android.epro.epro.base.BasePresenter
import com.app.android.epro.epro.mvp.contract.ProcessListContract
import com.app.android.epro.epro.mvp.model.ProcessListModel
import com.app.android.epro.epro.net.exception.ExceptionHandle
import okhttp3.RequestBody

class ProcessListPresenter : BasePresenter<ProcessListContract.View>(),
    ProcessListContract.Presenter {

    private val processListModel: ProcessListModel by lazy {
        ProcessListModel()
    }


    override fun requestApproveListData(body: RequestBody) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = processListModel.getApproveList(body).subscribe({ processListBean ->
            mRootView?.apply {
                dismissLoading()
                setProcessListData(processListBean)
            }
        }, { t ->
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })

        addSubscription(disposable)
    }

    override fun requestMoreListData(body: RequestBody) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = processListModel.getMoreList(body).subscribe({ processListBean ->
            mRootView?.apply {
                dismissLoading()
                setProcessListData(processListBean)
            }
        }, { t ->
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })

        addSubscription(disposable)
    }


    override fun requestDetailListData(map: HashMap<String, String>) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = processListModel.getDetailList(map).subscribe({ processListBean ->
            mRootView?.apply {
                dismissLoading()
                setProcessListData(processListBean)
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