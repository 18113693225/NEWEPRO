package com.app.android.epro.epro.mvp.model.bean

data class LoginBean(
    val `data`: Data,
    val message: String,
    val status: String,
    val code: Int
) {
    data class Data(
        val token: String
    )
}