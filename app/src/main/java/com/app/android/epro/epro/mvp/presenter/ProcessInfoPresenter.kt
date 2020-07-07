package com.app.android.epro.epro.mvp.presenter

import com.app.android.epro.epro.base.BasePresenter
import com.app.android.epro.epro.mvp.contract.ProcessInfoContract
import com.app.android.epro.epro.mvp.model.ProcessInfoModel
import com.app.android.epro.epro.net.exception.ExceptionHandle
import okhttp3.RequestBody


class ProcessInfoPresenter : BasePresenter<ProcessInfoContract.View>(),
    ProcessInfoContract.Presenter {

    private val processInfoModel: ProcessInfoModel by lazy {
        ProcessInfoModel()
    }

    override fun requestDetailInfoData(recordId: String, menuCode: String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getDetailInfoData(recordId, menuCode).subscribe({ any ->
                mRootView?.apply {
                    dismissLoading()
                    setDetailInfoData(any)
                }
            }, { t ->
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                }
            })

        addSubscription(disposable)
    }

    override fun goBack(approvalContent: String, flowApprovalSheetId: String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getGoBackData("2", approvalContent, flowApprovalSheetId)
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailBackData(any)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun pass(body: RequestBody) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getPassData(body)
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailBackData(any)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }


    override fun freeze(flowApprovalSheetId: String, flowFreezeState: String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getFreezeData(flowApprovalSheetId, flowFreezeState)
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailBackData(any)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }


    override fun recallMsg(messageId: String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getRecallMsgData(messageId)
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailBackData(any)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun keepMsg(body: RequestBody) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getKeepMsgData(body)
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailBackData(any)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun nextPeople(flowApprovalSheetId: String) {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getNextPeopleData(flowApprovalSheetId)
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailInfoData(any)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun infoNum() {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable =
            processInfoModel.getInfoNum()
                .subscribe({ any ->
                    mRootView?.apply {
                        dismissLoading()
                        setDetailInfoData(any)
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