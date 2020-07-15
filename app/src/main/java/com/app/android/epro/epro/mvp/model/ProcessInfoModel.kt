package com.app.android.epro.epro.mvp.model

import com.app.android.epro.epro.net.RetrofitManager
import com.app.android.epro.epro.rx.scheduler.SchedulerUtils
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody

class ProcessInfoModel {


    /**
     * 获取业务单详情数据
     * 根据menuCode进行不同的请求
     */
    fun getDetailInfoData(
        recordId: String,
        menuCode: String
    ): Observable<Any> {

        return when (menuCode) {
            "MENU_VEHICLE_MAINTENANCE_ADD" -> RetrofitManager.service.getDetailCarRepairData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_VEHICLE_APPLICATION_ADD" -> RetrofitManager.service.getDetailCarUseData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_INTRODUCE_LETTER_ADD" -> RetrofitManager.service.getIntroductionData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_BUSINESS_ADD" -> RetrofitManager.service.getProjectInitiationData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_BUSINESS_UPD" -> RetrofitManager.service.getProjectInitiationChangeData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_APPLICATION_FORM_ADD" -> RetrofitManager.service.getSalesInvoiceData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_TASKPANNLING_ADD" -> RetrofitManager.service.getProductionTaskData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_TAX_CERTIFICATE_OUT_ADD" -> RetrofitManager.service.getTaxCertificateData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_TAX_CERTIFICATE_BACK_ADD" -> RetrofitManager.service.getTaxCertificateBackData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_ITEM_PEOPLE_BUILD_ADD" -> RetrofitManager.service.getFormProjectPersonnelData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_ITEM_PEOPLE_DISPATCH_ADD" -> RetrofitManager.service.getProjectPersonnelSchedulerData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_WORKER_RECORD_BUILD_ADD" -> RetrofitManager.service.getFormProjectLaborPersonnelData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_SALES_CONTRACT_DRAFT_ADD" -> RetrofitManager.service.getDraftingOfContractData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_SALES_CONTRACT_DRAFT_UPD" -> RetrofitManager.service.getDraftingOfContractData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())
            "MENU_WORKER_DEDUCT_ADD" -> RetrofitManager.service.getDeductionOfLaborPersonnelData(
                recordId, menuCode
            ).compose(SchedulerUtils.ioToMain())


            else -> RetrofitManager.service.getDetailCarRepairData(recordId, menuCode)
                .compose(SchedulerUtils.ioToMain())
        }


    }

    /**
     * 消息总和（流程）
     */

    fun getInfoNum(): Observable<Any> {
        return RetrofitManager.service.getInfoNum().compose(SchedulerUtils.ioToMain())
    }


    /**
     * 审批（不同意）
     */

    fun getGoBackData(
        approvalFrom: String, approvalContent: String, flowApprovalSheetId: String
    ): Observable<Any> {
        return RetrofitManager.service.getGoBackData(
            approvalFrom, approvalContent, flowApprovalSheetId
        ).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 审批（同意）
     */

    fun getPassData(body: RequestBody): Observable<Any> {
        return RetrofitManager.service.getPassData(body).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 审批（冻结）
     */

    fun getFreezeData(flowApprovalSheetId: String, flowFreezeState: String): Observable<Any> {
        return RetrofitManager.service.getFreezeData(flowApprovalSheetId, flowFreezeState)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 审批（消息撤回）
     */

    fun getRecallMsgData(messageId: String): Observable<Any> {
        return RetrofitManager.service.getRecallMsgData(messageId)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 审批（消息保存）
     */

    fun getKeepMsgData(body: RequestBody): Observable<Any> {
        return RetrofitManager.service.getKeepMsgData(body).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 下一步审核人
     */

    fun getNextPeopleData(flowApprovalSheetId: String): Observable<Any> {
        return RetrofitManager.service.getNextPeopleData(flowApprovalSheetId)
            .compose(SchedulerUtils.ioToMain())
    }

}