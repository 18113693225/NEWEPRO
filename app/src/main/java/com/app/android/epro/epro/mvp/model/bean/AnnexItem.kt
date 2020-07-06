package com.app.android.epro.epro.mvp.model.bean

data class AnnexItem(
    val annexType: String,
    val items: List<Item>
) {
    data class Item(
        val fileId: String,
        val fileUrl: String,
        val thumbnailUrl: String,
        val attributeFacade: String,
        val fileName: String
    )
}