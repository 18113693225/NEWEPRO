package com.app.android.epro.epro.mvp.model.bean

data class ExtendData(
    val template: String,
    val extendFormData: List<ExtendFormData>,
    val templateId: String
) {
    data class ExtendFormData(
        val enteryKey: String,
        val languageType: String,
        val enteryValue: String
    )
}