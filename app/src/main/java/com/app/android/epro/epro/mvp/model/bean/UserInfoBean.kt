package com.app.android.epro.epro.mvp.model.bean

import java.io.Serializable

data class UserInfoBean(
    val `data`: Data,
    val message: String,
    val status: String,
    val code: Int
) : Serializable {
    data class Data(
        val birthday: Long,
        val departmentName: String,
        val address: String,
        val unitName: String,
        val jobItems: List<JobItem>,
        val sex: String,
        val companyName: String,
        val departmentId: String,
        val mobile: String,
        val JobName: String,
        val userName: String,
        val userAccountName: String,
        val jobId: String,
        val companyId: String,
        val contactNumber: String,
        val unitId: String,
        val userIdenty: String,
        val userType: String,
        val menus: List<Menu>,
        val headPortraitUrl: String
    ) : Serializable {
        data class JobItem(
            val jobName: String,
            val jobId: String,
            val jobType: String
        ) : Serializable

        data class Menu(
            val menuNameTxt: List<MenuNameTxt>,
            val menuCode: String,
            val subMenus: List<SubMenu>
        ) : Serializable {
            data class MenuNameTxt(
                val languageType: String,
                val menuNam: String
            ) : Serializable

            data class SubMenu(
                val menuNameTxt: List<MenuNameTxt>,
                val menuCode: String
            ) : Serializable {
                data class MenuNameTxt(
                    val languageType: String,
                    val menuNam: String
                ) : Serializable
            }
        }
    }
}