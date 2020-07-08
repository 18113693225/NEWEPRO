package com.app.android.epro.epro.api

import com.app.android.epro.epro.mvp.model.bean.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    /**
     * 登陆
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("login/v1/onlogin")
    fun getLoginData(@Body body: RequestBody): Observable<LoginBean>


    /**
     * 用户信息
     */
    @GET("mobile/login/v1/getuserinfo")
    fun getUserInfoData(): Observable<UserInfoBean>

    /**
     * 消息总和（流程）
     */
    @POST("supervisedView/v1/getViewNumber")
    fun getInfoNum(): Observable<ProcessNumBean>

    /**
     * 更新单子查看状态
     */
    @GET("supervisedView/v1/updateReadState")
    fun postRead(@Query("supervisedViewId") supervisedViewId: String):
            Observable<ProcessBean>


    /**
     * 审核列表（1待审核2已审核）
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("supervisedView/v1/getApprovalStatementsList")
    fun getApproveListData(@Body body: RequestBody): Observable<ProcessListBean>


    /**
     * 询问 退回 监督列表（1未看2已看）
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("supervisedView/v1/getList")
    fun getMoreListData(@Body body: RequestBody): Observable<ProcessListBean>


    /**
     * 待办列表（1待处理2已处理）
     */
    @GET("supervisedView/v1/getWaitDealList")
    fun getDetailListData(@QueryMap map: HashMap<String, String>): Observable<ProcessListBean>


    /**
     * 审批（不同意/退回）
     */
    @GET("flowApprovalSheet/v1/flowApprovalNotPass")
    fun getGoBackData(
        @Query("approvalFrom") approvalFrom: String,
        @Query("approvalContent") approvalContent: String,
        @Query("flowApprovalSheetId") flowApprovalSheetId: String
    ): Observable<ProcessBean>


    /**
     * 审批（同意/处理）
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("flowApprovalSheet/v1/flowApprovalPass")
    fun getPassData(@Body body: RequestBody): Observable<ProcessBean>


    /**
     * 审批（消息撤回）
     */
    @GET("flowApprovalSheet/v1/recallMessage")
    fun getRecallMsgData(
        @Query("messageId") messageId: String
    ): Observable<ProcessBean>

    /**
     * 审批（冻结/解冻）flowFreezeState 冻结状态：1解冻，2冻结
     */
    @GET("flowSetSupervise/v1/setFlowFreeze")
    fun getFreezeData(
        @Query("flowApprovalSheetId") flowApprovalSheetId: String,
        @Query("flowFreezeState") flowFreezeState: String
    ): Observable<ProcessBean>


    /**
     * 审批（消息保存）
     */
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("flowApprovalSheet/v1/saveMessage")
    fun getKeepMsgData(@Body body: RequestBody): Observable<ProcessBean>

    /**
     * 审批（获取下一步审核人）
     */
    @GET("flowApprovalSheet/v1/getNextStepPeople")
    fun getNextPeopleData(
        @Query("flowApprovalSheetId") flowApprovalSheetId: String
    ): Observable<NextPeopleBean>


    /**
     * 车辆维修/保养
     */
    @GET("app/v1/getApprovalReferenceInfo")
    fun getDetailCarRepairData(
        @Query("recordId") recordId: String,
        @Query("menuCode") menuCode: String
    ): Observable<DetailCarRepairBean>


    /**
     * 用车申请
     */
    @GET("app/v1/getApprovalReferenceInfo")
    fun getDetailCarUseData(
        @Query("recordId") recordId: String,
        @Query("menuCode") menuCode: String
    ): Observable<DetailCarUseBean>

    /**
     * 介绍信/委托书
     */
    @GET("app/v1/getApprovalReferenceInfo")
    fun getIntroductionData(
        @Query("recordId") recordId: String,
        @Query("menuCode") menuCode: String
    ): Observable<DetailIntroductionBean>

    /**
     * 项目立项备案
     */
    @GET("app/v1/getApprovalReferenceInfo")
    fun getProjectInitiationData(
        @Query("recordId") recordId: String,
        @Query("menuCode") menuCode: String
    ): Observable<DetailProjectInitiationBean>

    /**
     * 项目立项备案变更
     */
    @GET("app/v1/getApprovalReferenceInfo")
    fun getProjectInitiationChangeData(
        @Query("recordId") recordId: String,
        @Query("menuCode") menuCode: String
    ): Observable<DetailProjectInitiationBean>


}