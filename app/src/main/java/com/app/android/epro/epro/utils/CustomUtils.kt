package com.app.android.epro.epro.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.EditText
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.app.android.epro.epro.R
import com.app.android.epro.epro.ui.activity.ApprovalActivity
import com.app.android.epro.epro.ui.activity.LoginActivity
import com.app.android.epro.epro.ui.activity.ProcessManageActivity
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject

object CustomUtils {

    private var dialog: MaterialDialog? = null
    const val pageSize: Int = 20
    const val emptyInfo: String = "未填写"

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