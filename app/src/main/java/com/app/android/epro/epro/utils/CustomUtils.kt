package com.app.android.epro.epro.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.app.android.epro.epro.R
import com.app.android.epro.epro.ui.activity.*
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

object CustomUtils {

    private var dialog: MaterialDialog? = null
    const val pageSize: Int = 20
    const val emptyInfo: String = "未填写"


    fun errHandle(code: Int, msg: String, activity: Activity) {
        when (code) {
            401 -> toLogin(activity)
            else ->
                Toasty.error(activity, msg).show()
        }
    }


    fun jump(
        activity: Activity,
        menu: String, id: String, jobId: String, from: String
    ) {
        when (menu) {
            "MENU_VEHICLE_MAINTENANCE_ADD" -> toAny(
                activity,
                DetailCarRepairActivity().javaClass,
                menu, id, jobId, from
            )
            "MENU_VEHICLE_APPLICATION_ADD" -> toAny(
                activity,
                DetailCarUseActivity().javaClass,
                menu, id, jobId, from
            )
            "MENU_INTRODUCE_LETTER_ADD" -> toAny(
                activity,
                DetailIntroductionLetterActivity().javaClass,
                menu, id, jobId, from
            )
            "MENU_BUSINESS_ADD" -> toAny(
                activity,
                DetailProjectInitiationActivity().javaClass,
                menu, id, jobId, from
            )
            "MENU_BUSINESS_UPD" -> toAny(
                activity,
                DetailProjectInitiationChangeActivity().javaClass,
                menu, id, jobId, from
            )
            "MENU_APPLICATION_FORM_ADD" -> toAny(
                activity,
                DetailSaleInvoiceActivity().javaClass,
                menu, id, jobId, from
            )
            "MENU_TASKPANNLING_ADD" -> toAny(
                activity,
                DetailProductionTaskPlanActivity().javaClass,
                menu, id, jobId, from
            )

        }
    }


    private fun toAny(
        activity: Activity, cls: Class<Any>,
        menu: String, id: String,
        jobId: String, from: String
    ) {
        val intent = Intent(activity, cls)
        intent.putExtra("menu", menu)
        intent.putExtra("id", id)
        intent.putExtra("jobId", jobId)
        intent.putExtra("from", from)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)

    }


    fun toBody(map: HashMap<String, String>): RequestBody {
        val jsonObject = JSONObject(map as Map<*, *>)
        return RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject.toString())

    }

    fun toBody(body: Any): RequestBody {
        val str = Gson().toJson(body)
        return RequestBody.create("application/json".toMediaTypeOrNull(), str)

    }

    fun toLogin(activity: Activity) {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    fun toApproval(activity: Activity, flowApprovalSheetId: String, jobId: String) {
        val intent = Intent(activity, ApprovalActivity::class.java)
        intent.putExtra("flowApprovalSheetId", flowApprovalSheetId)
        intent.putExtra("jobId", jobId)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    fun toProcessManageActivity(activity: Activity) {
        val intent = Intent(activity, ProcessManageActivity::class.java)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    /**
     * 数据上传dialog
     */

    fun showPostDialog(context: Context) {
        if (null == dialog) {
            dialog = MaterialDialog(context)
                .title(R.string.reminder)
                .message(R.string.data_request)
                .cornerRadius(8f)
                .cancelOnTouchOutside(false)
            dialog!!.show()
        } else {
            dialog!!.show()
        }

    }


    fun dismissDialog() {
        dialog?.dismiss()
        dialog = null
    }

}